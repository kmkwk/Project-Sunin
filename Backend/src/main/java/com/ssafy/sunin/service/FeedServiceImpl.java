package com.ssafy.sunin.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.ssafy.sunin.domain.FeedCollections;
import com.ssafy.sunin.domain.user.User;
import com.ssafy.sunin.dto.feed.*;
import com.ssafy.sunin.dto.user.UserProfile;
import com.ssafy.sunin.repository.FeedRepository;
import com.ssafy.sunin.repository.FollowerRepository;
import com.ssafy.sunin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {

    private final FeedRepository feedRepository;
    private final FollowerRepository followerRepository;
    private final UserRepository userRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.s3.url}")
    private String url;

    private final AmazonS3 amazonS3;

    @Override
    public FeedDto writeImageFeed(FeedWrite feedWrite) {
        List<String> fileList = new ArrayList<>();
        List<MultipartFile> files = feedWrite.getFiles();
        if (files != null) {
            AwsFile(feedWrite.getFiles(), fileList);
        }

        User user = userRepository.findProfileByUserSeq(feedWrite.getUserId());
        FeedCollections feedCollections = FeedCollections.setFeedCollection(feedWrite,fileList);

        feedRepository.save(feedCollections);
        suninDays(feedWrite.getUserId());

        return FeedDto.feedDto(feedCollections,user);
    }

    private void suninDays(Long userId) {
        User user = userRepository.findProfileByUserSeq(userId);
        user.setSuninDayIncrease();
        userRepository.save(user);
    }

    @Override
    public List<String> downloadFileFeed(String fileNames) {
        ObjectListing objectListing = amazonS3.listObjects(bucket);
        List<String> arrayKeyList = new ArrayList<>();
        List<Date> arrayModTimeList = new ArrayList<>();
        List<String> fileNameList = new ArrayList<>();
        for (S3ObjectSummary s : objectListing.getObjectSummaries()) {
            arrayKeyList.add(s.getKey());
            arrayModTimeList.add(s.getLastModified());
        }
        Date max = Collections.max(arrayModTimeList);
        String fileName = arrayKeyList.get(arrayModTimeList.indexOf(max));

        fileNameList.add(String.format(url + "/%s", fileNames));
        return fileNameList;
    }

    @Override
    public FeedDto updateFile(FileUpdate fileUpdate) {
        Optional<FeedCollections> feed = feedRepository.findByIdAndUserId(new ObjectId(fileUpdate.getId()), fileUpdate.getUserId());
        if (feed.isPresent()) {
            fileUpdate.getFiles().forEach(file -> {
                amazonS3.deleteObject(new DeleteObjectRequest(bucket, file));
                feed.get().getFilePath().remove(file);
            });

            User user = userRepository.findProfileByUserSeq(fileUpdate.getUserId());
            feed.get().setFileModified(feed.get().getFilePath());
            FeedCollections feedCollections = feedRepository.save(feed.get());
            return FeedDto.feedDto(feedCollections,user);
        }

        return null;
    }

    @Override
    public FeedDto addFile(FeedFile feedFile) {
        Optional<FeedCollections> feed = feedRepository.findByIdAndUserId(new ObjectId(feedFile.getId()), feedFile.getUserId());
        if (feed.isPresent()) {
            List<String> fileList = AwsFile(feedFile.getFiles(), feed.get().getFilePath());
            feed.get().setFileModified(fileList);
            FeedCollections feedCollections = feedRepository.save(feed.get());
            User user = userRepository.findProfileByUserSeq(feedFile.getUserId());
            return FeedDto.feedDto(feedCollections,user);
        }

        return null;
    }

    private List<String> AwsFile(List<MultipartFile> files, List<String> list) {
        files.forEach(file -> {
            String fileName = createFileName(file.getOriginalFilename());
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(file.getSize());
            objectMetadata.setContentType(file.getContentType());

            try (InputStream inputStream = file.getInputStream()) {
                amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드에 실패했습니다.");
            }
            list.add(String.format(url + "/%s", fileName));
        });

        return list;
    }

    @Override
    public FeedDto getDetailFeed(String id) {
        FeedCollections feedCollections = feedRepository.findByIdAndFlagTrue(new ObjectId(String.valueOf(id)));
        User user = userRepository.findProfileByUserSeq(feedCollections.getUserId());

        return FeedDto.feedDto(feedCollections,user);
    }

    @Override
    public FeedDto updateFeed(FeedUpdate feedUpdate) {
        FeedCollections feedCollections = feedRepository.findByIdAndUserIdAndFlagTrue(new ObjectId(feedUpdate.getId()), feedUpdate.getUserId());
        feedCollections.setFeedModified(feedUpdate);
        feedRepository.save(feedCollections);

        User user = userRepository.findProfileByUserSeq(feedCollections.getUserId());
        return FeedDto.feedDto(feedCollections,user);
    }

    @Override
    public void deleteFeed(String id, Long userId) {
        FeedCollections feedCollections = feedRepository.findByIdAndUserIdAndFlagTrue(new ObjectId(id), userId);
        feedCollections.setFeedDelete();
        feedRepository.save(feedCollections);
    }

    @Override
    public List<FeedDto> getFollowerFeed(Long userId) {
        // TODO :  1. 시간 구하는 쿼리 (startDate ,endDate)
        List<Long> followers = followerRepository.getFollowingList(userId);
        Map<Long, User> userMap = userRepository.findFollowerListByUserSeqIn(followers).stream()
                .collect(Collectors.toMap(
                        User::getUserSeq,
                        o -> o
                )); 

        return feedRepository.getFollowerFeed(followers).stream()
                .map(feedCollections -> FeedDto.builder()
                                .id(feedCollections.getId().toString())
                                .content(feedCollections.getContent())
                                .hashtags(feedCollections.getHashtags())
                                .likes(feedCollections.getLikes())
                                .createdDate(feedCollections.getCreatedDate())
                                .modifiedDate(feedCollections.getModifiedDate())
                                .likeUser(feedCollections.getLikeUser())
                                .filePath(feedCollections.getFilePath())
                                .user(userMap.get(feedCollections.getUserId()))
                                .build())
                                .collect(Collectors.toList());
    }


    @Override
    public Page<FeedDto> getLatestFeed(Pageable pageable, Long userId) {
        return feedRepository.findAllByUserId(pageable, userId);
    }

    @Override
    public Page<FeedDto> getLikeFeed(Pageable pageable, Long userId) {
        return feedRepository.findAllByUserId(pageable, userId);
    }

    @Override
    public void likeFeed(FeedLike feedLike) {
        FeedCollections feedCollections = feedRepository.findByIdAndFlagTrue(new ObjectId(feedLike.getId()));
        Map<Long, Object> users = new HashMap<>(feedCollections.getLikeUser());

        int like = feedCollections.getLikes();

        if (users.containsKey(feedLike.getUserId())) {
            users.remove(feedLike.getUserId());
            like--;
        } else {
            users.put(feedLike.getUserId(), true);
            like++;
        }

        feedCollections.setLikeModified(like, users);

        feedRepository.save(feedCollections);
    }

    @Override
    public List<UserProfile> getLikeUserList(String id) {
        FeedCollections feedCollections = feedRepository.findByIdAndFlagTrue(new ObjectId(id));
        Set<Long> list = feedCollections.getLikeUser().keySet();

        return userRepository.findFollowerSetByUserSeqIn(list)
                .stream()
                .map(user -> UserProfile.builder()
                        .id(user.getUserSeq())
                        .nickName(user.getUserNickname())
                        .image(user.getProfileImageUrl())
                        .build()).collect(Collectors.toList());

    }

    private String createFileName(String fileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    private String getFileExtension(String fileName) {
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일(" + fileName + ") 입니다.");
        }
    }
}