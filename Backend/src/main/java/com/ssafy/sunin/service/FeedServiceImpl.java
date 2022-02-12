package com.ssafy.sunin.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.ssafy.sunin.domain.Comment;
import com.ssafy.sunin.domain.FeedCollections;
import com.ssafy.sunin.domain.user.User;
import com.ssafy.sunin.payload.request.feed.*;
import com.ssafy.sunin.payload.response.comment.CommentDto;
import com.ssafy.sunin.payload.response.user.UserDetailProfile;
import com.ssafy.sunin.payload.response.feed.FeedCommentDto;
import com.ssafy.sunin.payload.response.feed.FeedDto;
import com.ssafy.sunin.repository.FeedRepository;
import com.ssafy.sunin.repository.FollowerRepository;
import com.ssafy.sunin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Map.Entry;

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
    public FeedCommentDto getDetailFeed(String id) {
        FeedCollections feedCollections = feedRepository.findByIdAndFlagTrue(new ObjectId(String.valueOf(id)));
        User user = userRepository.findProfileByUserSeq(feedCollections.getUserId());

        // 댓글쓴 사람 프로필정보까지 같이 보내줘야함
        // 우선 댓글에 대한 전체 정보를 가져와서
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.ASC, "writeDate"));
        orders.add(new Sort.Order(Sort.Direction.ASC, "group"));
        orders.add(new Sort.Order(Sort.Direction.ASC, "order"));
        // 해당 피드의 전체 댓글
        Map<Object, Comment> commentsMap = feedRepository.findFeedSortIdByIdAndFlagTrue(feedCollections.getId(), Sort.by(orders)).getComments();

        List<Comment> commentsList = new ArrayList<>();
        List<Object> commentKey = new ArrayList<>();
        // Comment key값
        commentKey.addAll(commentsMap.keySet());
        // Comment value값
        commentsList.addAll(commentsMap.values());

        // 아이디 중복 처리
        Set<Long> setUser = commentsList.stream()
                .map(Comment::getWriter)
                .collect(Collectors.toSet());

        // 댓글의 유저의 프로필 정보
        Map<Long, User> userMap = userRepository.findAllSetByUserSeqIn(setUser).stream()
                .collect(Collectors.toMap(
                        User::getUserSeq,
                        o -> o
                ));
        // 댓글 유저 프로필 정보랑 댓글 조합
        List<CommentDto> comments = CommentDto.mapCommentDto(commentsList,userMap);
        Map<Object,CommentDto> commentMap = new HashMap<>();

        for (int i = 0; i < comments.size(); i++) {
            commentMap.put(commentKey.get(i),comments.get(i));
        }

        // 댓글 최신순 정렬
        List<Entry<Object,CommentDto>> entryList = new ArrayList<Entry<Object,CommentDto>>(commentMap.entrySet());
        entryList.sort(
                Comparator.comparing(o -> o.getValue().getWriteDate())
        );

        LinkedHashMap<Object,CommentDto> linkedHashMap = new LinkedHashMap<>();
        for (int i = 0; i < entryList.size(); i++) {
            linkedHashMap.put(entryList.get(i).getKey(),entryList.get(i).getValue());
        }

        return FeedCommentDto.feedCommentDto(feedCollections, user, linkedHashMap);
    }

    @Override
    public FeedDto updateFeed(FeedUpdate feedUpdate) {
        FeedCollections feedCollections = feedRepository.findByIdAndUserIdAndFlagTrue(new ObjectId(feedUpdate.getId()), feedUpdate.getUserId());
        List<String> fileNames = new ArrayList<>();
        // Todo : aws에선 중복 파일은 제거하던가, 안올려야함
        AwsFile(feedUpdate.getFiles(),fileNames);
        feedCollections.setFileModified(fileNames);
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

    // 나의 팔로워 최신순
    @Override
    public List<FeedDto> getFollowerLatestFeed(Long userId) {
        // TODO :  1. 시간 구하는 쿼리 (startDate ,endDate)
        //  로그인시 로그인시간을 로깅하는 테이블을 만든다. - mysql
        //  내가 처음에 로그인 했을때 로깅 테이블에서 로그인한 시간 최신순 두개의 시간을 가져온다.
        //  ex) 로깅 테이블에 저장된 시간이 오전 12시,오후 1시, 오후 2시, 오후 5시라면 오후2시 오후 5시를 가져온다.
        //  나의 유저의 팔로워 리스트를 구한다.
        //  몽고에서 나의 유저 팔로워들의 오후 2시 오후 5시 사이의 피드를 가져온다.

        List<Long> followers = followerRepository.getFollowingList(userId);
        Map<Long, User> userMap = userRepository.findAllListByUserSeqIn(followers).stream()
                .collect(Collectors.toMap(
                        User::getUserSeq,
                        o -> o
                ));
        List<FeedCollections> feedCollections = feedRepository.getFollowerLatesFeed(followers);
        return FeedDto.mapFeedDto(feedCollections,userMap);
    }

    // 나의 팔로워 좋아요 순
    @Override
    public List<FeedDto> getFollowerLikeFeed(Long userId) {
        // 나의 팔로워
        List<Long> followers = followerRepository.getFollowingList(userId);
        // 유저 전체 정보
        Map<Long, User> userMap = userRepository.findAllListByUserSeqIn(followers).stream()
                .collect(Collectors.toMap(
                        User::getUserSeq,
                        o -> o
                ));

        List<FeedCollections> feedCollections = feedRepository.getFollowerLikeFeed(followers);
        return FeedDto.mapFeedDto(feedCollections,userMap);
    }

    // 나의 피드 프로필용
    @Override
    public List<FeedDto> getPersonalFeed(Long userId) {
        List<FeedCollections> feedCollections = feedRepository.getPersonalFeed(userId);
        User user = userRepository.findProfileByUserSeq(userId);

        return FeedDto.personFeedDto(feedCollections,user);
    }

    // 전체 최신순
    @Override
    public List<FeedDto> getLatestFeed(Pageable pageable) {
         List<FeedCollections> feedCollection = feedRepository.findAllByFlagTrue(pageable);

         Set<Long> users = feedCollection.stream()
                 .map(FeedCollections::getUserId)
                 .collect(Collectors.toSet());

         Map<Long, User> userMap = userRepository.findAllSetByUserSeqIn(users).stream()
                 .collect(Collectors.toMap(
                         User::getUserSeq,
                         o -> o
                 ));
         return FeedDto.mapFeedDto(feedCollection,userMap);
    }

    // 전체 좋아요 순
    @Override
    public List<FeedDto> getLikeFeed(Pageable pageable) {
        List<FeedCollections> feedCollection = feedRepository.findAllByFlagTrue(pageable);

         Set<Long> users = feedCollection.stream()
                 .map(FeedCollections::getUserId)
                 .collect(Collectors.toSet());

         Map<Long, User> userMap = userRepository.findAllSetByUserSeqIn(users).stream()
                 .collect(Collectors.toMap(
                         User::getUserSeq,
                         o -> o
                 ));
         return FeedDto.mapFeedDto(feedCollection,userMap);
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
    public List<UserDetailProfile> getLikeUserList(String id) {
        FeedCollections feedCollections = feedRepository.findByIdAndFlagTrue(new ObjectId(id));
        Set<Long> list = feedCollections.getLikeUser().keySet();

        return userRepository.findFollowerSetByUserSeqIn(list)
                .stream()
                .map(user -> UserDetailProfile.builder()
                        .id(user.getUserSeq())
                        .nickName(user.getUserNickname())
                        .image(user.getProfileImageUrl())
                        .build()).collect(Collectors.toList());

    }

    @Override
    public Long feedCount(Long userId) {
        return feedRepository.getFeedCount(userId);
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