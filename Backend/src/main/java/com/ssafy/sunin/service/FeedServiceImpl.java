package com.ssafy.sunin.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ssafy.sunin.domain.FeedCollections;
import com.ssafy.sunin.domain.Follower;
import com.ssafy.sunin.dto.*;
import com.ssafy.sunin.repository.FeedRepository;
import com.ssafy.sunin.repository.FollowerRepository;
import com.ssafy.sunin.repository.FollowerRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {

    private final FeedRepository feedRepository;
    private final MongoTemplate mongoTemplate;
    private final FollowerRepository followerRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;

    @Override
    public FeedDto writeImageFeed(FeedVO feedVO) {
        List<String> fileNameList = new ArrayList<>();
        // forEach 구문을 통해 multipartFile로 넘어온 파일들 하나씩 fileNameList에 추가
        feedVO.getFiles().forEach(file -> {
            String fileName = createFileName(file.getOriginalFilename());
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(file.getSize());
            objectMetadata.setContentType(file.getContentType());

            try(InputStream inputStream = file.getInputStream()) {
                amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
            } catch(IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드에 실패했습니다.");
            }

            fileNameList.add(String.format("https://sunin-bucket.s3.ap-northeast-2.amazonaws.com/%s",fileName));
        });

        FeedCollections feedCollections = FeedCollections.builder()
                .userId(feedVO.getUserId())
                .content(feedVO.getContent())
                .hashtags(feedVO.getHashtags())
                .likes(0)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .flag(true)
                .filePath(fileNameList)
                .build();
        ObjectId id = feedRepository.save(feedCollections).getId();

        return FeedDto.builder()
                .id(id.toString())
                .userId(feedCollections.getUserId())
                .content(feedCollections.getContent())
                .likes(feedCollections.getLikes())
                .hashtags(feedCollections.getHashtags())
                .createdDate(feedCollections.getCreatedDate())
                .modifiedDate(feedCollections.getLastModifiedDate())
                .filePath(feedCollections.getFilePath())
                .build();
    }

    @Override
    public FeedDto getDetailFeed(String id) {
        Optional<FeedCollections> feed = feedRepository.findById(new ObjectId(String.valueOf(id)));
        FeedCollections feedCollections = feed.get();

        return FeedDto.builder()
                .id(feedCollections.getId().toString())
                .userId(feedCollections.getUserId())
                .hashtags(feedCollections.getHashtags())
                .likes(feedCollections.getLikes())
                .createdDate(feedCollections.getCreatedDate())
                .modifiedDate(feedCollections.getLastModifiedDate())
                .filePath(feedCollections.getFilePath())
                .content(feedCollections.getContent())
                .build();
    }

    @Override
    public List<FeedDto> getUserListAllFeed(String userId) {
        return feedRepository.findAllByUserId(userId)
                .stream()
                .map(feedCollections -> FeedDto.builder()
                        .id(feedCollections.getId().toString())
                        .userId(feedCollections.getUserId())
                        .content(feedCollections.getContent())
                        .hashtags(feedCollections.getHashtags())
                        .likes(feedCollections.getLikes())
                        .createdDate(feedCollections.getCreatedDate())
                        .modifiedDate(feedCollections.getLastModifiedDate())
                        .build()).collect(Collectors.toList());
    }

    @Override
    public List<FeedDto> getCompanyListAllFeed(String companyId) {
        return feedRepository.findAllByUserId(companyId)
                .stream()
                .map(feedCollections -> FeedDto.builder()
                        .id(feedCollections.getId().toString())
                        .userId(feedCollections.getUserId())
                        .content(feedCollections.getContent())
                        .hashtags(feedCollections.getHashtags())
                        .likes(feedCollections.getLikes())
                        .createdDate(feedCollections.getCreatedDate())
                        .modifiedDate(feedCollections.getLastModifiedDate())
                        .build()).collect(Collectors.toList());
    }

    @Override
    public List<FeedDto> getListAllFeed() {
        return feedRepository.findAll()
                .stream()
                .map(feedCollections -> FeedDto.builder()
                        .id(feedCollections.getId().toString())
                        .userId(feedCollections.getUserId())
                        .content(feedCollections.getContent())
                        .hashtags(feedCollections.getHashtags())
                        .likes(feedCollections.getLikes())
                        .createdDate(feedCollections.getCreatedDate())
                        .modifiedDate(feedCollections.getLastModifiedDate())
                        .build()).collect(Collectors.toList());
    }

    @Override
    public FeedDto updateFeed(FeedUpdate feedUpdate) {
        Query query = new Query(Criteria.where("_id").is(feedUpdate.getId()));
        Update update = new Update();
        Optional<FeedCollections> list = feedRepository.findById(feedUpdate.getId());
        FeedCollections feedCollections  = list.get();
        LocalDateTime lt = LocalDateTime.now();
        update.set("content", feedUpdate.getContent());
        update.set("hashtags", feedUpdate.getHashtags());
        update.set("modifiedDate",lt);
        mongoTemplate.updateMulti(query, update, FeedCollections.class);

        return FeedDto.builder()
                .id(feedCollections.getId().toString())
                .userId(feedCollections.getUserId())
                .hashtags(feedCollections.getHashtags())
                .likes(feedCollections.getLikes())
                .content(feedUpdate.getContent())
                .createdDate(feedCollections.getCreatedDate())
                .modifiedDate(lt)
                .build();
    }

    @Override
    public void deleteFeed(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();
        update.set("flag", false);
        update.set("modifiedDate",LocalDateTime.now());
        mongoTemplate.updateFirst(query, update, FeedCollections.class);
    }

    // Todo : 나의 팔로워 피드 조회
    // 내 userid 기준으로 팔로워 되있는 사람들 보여줌
    @Override
    public List<FeedDto> getFollowerFeed(Long id) {
        // 나의 팔로워 리스트
        // 현재 팔로워들의 닉네임 값들이 저장되어 있음
//        List<String> followers = followerRepository.getFollowingList(id);
//        System.out.println(followers);
        List<String> followers = followerRepository.getFollowingList(id);
        // 이걸 가지고 몽고에서 게시글 조회하면됨
        // 1. for문으로 할지?
        // 2.
        // 기준점을 만들어야될지? ex) 가장 최근에 팔로우 한 사람들 중 3명꺼만 - 어짜피 동일
//        List<FeedCollections> feedCollections = feedRepository.findAllByUserIdIn(followers);
//        System.out.println(feedCollections);
        return feedRepository.getFollowerFeed(followers)
                .stream()
                .map(feedCollections -> FeedDto.builder()
                        .id(feedCollections.getId().toString())
                        .userId(feedCollections.getUserId())
                        .content(feedCollections.getContent())
                        .hashtags(feedCollections.getHashtags())
                        .likes(feedCollections.getLikes())
                        .createdDate(feedCollections.getCreatedDate())
                        .modifiedDate(feedCollections.getLastModifiedDate())
                        .build()).collect(Collectors.toList());
    }

    // Todo : 최신 선택 피드 조회
    // created 기준으로 내림차순해서 보여줌
    @Override
    public List<FeedDto> getLatestFeed(FeedList feedList) {
        List<FeedCollections> feed = feedRepository.getLatestFeed(feedList);
        return feed.stream()
                .map(feedCollections -> FeedDto.builder()
                        .id(feedCollections.getId().toString())
                        .userId(feedCollections.getUserId())
                        .content(feedCollections.getContent())
                        .likes(feedCollections.getLikes())
                        .filePath(feedCollections.getFilePath())
                        .hashtags(feedCollections.getHashtags())
                        .createdDate(feedCollections.getCreatedDate())
                        .modifiedDate(feedCollections.getLastModifiedDate())
                        .build()).collect(Collectors.toList());
    }

    @Override
    public List<FeedDto> getPageLatestFeed(Pageable pageable) {
        Page<FeedCollections> feed = feedRepository.findAll(pageable);
        return feed.stream()
                .map(feedCollections -> FeedDto.builder()
                        .id(feedCollections.getId().toString())
                        .userId(feedCollections.getUserId())
                        .content(feedCollections.getContent())
                        .likes(feedCollections.getLikes())
                        .filePath(feedCollections.getFilePath())
                        .hashtags(feedCollections.getHashtags())
                        .createdDate(feedCollections.getCreatedDate())
                        .modifiedDate(feedCollections.getLastModifiedDate())
                        .build()).collect(Collectors.toList());
    }

    // Todo : 좋아요순 선택 피드 조회
    // 좋아요 내림차순으로 보여줌
    @Override
    public List<FeedDto> getLikeFeed(FeedList feedList) {
        List<FeedCollections> feed = feedRepository.getLikeFeed(feedList);
        return feed.stream()
                .map(feedCollections -> FeedDto.builder()
                        .id(feedCollections.getId().toString())
                        .userId(feedCollections.getUserId())
                        .content(feedCollections.getContent())
                        .likes(feedCollections.getLikes())
                        .filePath(feedCollections.getFilePath())
                        .hashtags(feedCollections.getHashtags())
                        .createdDate(feedCollections.getCreatedDate())
                        .modifiedDate(feedCollections.getLastModifiedDate())
                        .build()).collect(Collectors.toList());
    }

    @Override
    public List<FeedDto> getPageLikeFeed(FeedPage feedPage) {
        Page<FeedCollections> feed = feedRepository.findAll(feedPage.getPageable());
        return feed.stream()
                .map(feedCollections -> FeedDto.builder()
                        .id(feedCollections.getId().toString())
                        .userId(feedCollections.getUserId())
                        .content(feedCollections.getContent())
                        .likes(feedCollections.getLikes())
                        .filePath(feedCollections.getFilePath())
                        .hashtags(feedCollections.getHashtags())
                        .createdDate(feedCollections.getCreatedDate())
                        .modifiedDate(feedCollections.getLastModifiedDate())
                        .build()).collect(Collectors.toList());
    }

    @Override
    public FeedDto likeFeed(FeedLike feedLike) {
        Query query = new Query(Criteria.where("_id").is(feedLike.getId()));
        Update update = new Update();
        Optional<FeedCollections> user = feedRepository.findById(new ObjectId(String.valueOf(feedLike.getId())));
        FeedCollections feedCollections = user.get();
        Map<String,Object> users = new HashMap<>();
        users.putAll(feedCollections.getLikeUser());
        int like = feedLike.getLikes();
        // 좋아요 누른상태
        if(users.containsKey(feedLike.getUser())){
            users.remove(feedLike.getUser());
            update.set("likes", --like);
        }else{
            users.put(feedLike.getUser(),true);
            update.set("likes", ++like);
        }
        update.set("likeUser",users);

        mongoTemplate.upsert(query,update,FeedCollections.class);

        return FeedDto.builder()
                .id(feedLike.getId())
                .userId(feedLike.getUser())
                .likeUser(users)
                .likes(like).build();
    }

    @Override
    public String commitSunin(String userId) {
        Query query = new Query(Criteria.where("userId").is(userId));
        Update update = new Update();
        return null;
    }

    @Override
    public void deleteFile(String fileName) {
        amazonS3.deleteObject(new DeleteObjectRequest(bucket, fileName));
    }

    private String createFileName(String fileName) { // 먼저 파일 업로드 시, 파일명을 난수화하기 위해 random으로 돌립니다.
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    private String getFileExtension(String fileName) { // file 형식이 잘못된 경우를 확인하기 위해 만들어진 로직이며, 파일 타입과 상관없이 업로드할 수 있게 하기 위해 .의 존재 유무만 판단하였습니다.
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일(" + fileName + ") 입니다.");
        }
    }
}
