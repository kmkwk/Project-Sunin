package com.ssafy.sunin.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ssafy.sunin.domain.FeedCollections;
import com.ssafy.sunin.dto.FeedDto;
import com.ssafy.sunin.dto.FeedVO;
import com.ssafy.sunin.repository.FeedRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {

    private final FeedRepository feedRepository;
    private final MongoTemplate mongoTemplate;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;

    @Override
    public List<FeedDto> getListFeed() {
        return feedRepository.findAll()
                .stream()
                .map(feedCollections -> FeedDto.builder()
                        .id(feedCollections.getId())
                        .userId(feedCollections.getUserId())
                        .content(feedCollections.getContent())
                        .hashtags(feedCollections.getHashtags())
                        .likes(feedCollections.getLikes())
                        .createdDate(feedCollections.getCreatedDate())
                        .modifiedDate(feedCollections.getLastModifiedDate())
                        .build()).collect(Collectors.toList());
    }

    @Override
    public FeedDto getFeed(String id) {
        Optional<FeedCollections> list = feedRepository.findById(id);
        FeedCollections feedCollections = list.get();

        return FeedDto.builder()
                .id(feedCollections.getId())
                .userId(feedCollections.getUserId())
                .hashtags(feedCollections.getHashtags())
                .likes(feedCollections.getLikes())
                .content(feedCollections.getContent())
                .createdDate(feedCollections.getCreatedDate())
                .modifiedDate(feedCollections.getLastModifiedDate())
                .build();
    }

    @Override
    public FeedDto updateFeed(FeedCollections feedCollections) {

//        Query query = new Query(Criteria.where("_id").is(feedCollections.getId()));
//        Update update = new Update();
//        update.set("content", feedCollections.getContent());
//        update.set("hashtags", feedCollections.getHashtags());
//        mongoTemplate.updateFirst(query, update, "FeedCollections");
        return FeedDto.builder()
                .id(feedCollections.getId())
                .userId(feedCollections.getUserId())
                .hashtags(feedCollections.getHashtags())
                .likes(feedCollections.getLikes())
                .content(feedCollections.getContent())
                .createdDate(feedCollections.getCreatedDate())
                .modifiedDate(feedCollections.getLastModifiedDate())
                .build();
    }

    @Override
    public void deleteFeed(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();
        update.set("flag", false);
        mongoTemplate.updateFirst(query, update, "FeedCollections");
    }

    @Override
    public FeedDto userFeed(String userId) {
        return null;
    }

    /*
    파일 입출력
     */

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
                .id(id)
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
    public FeedCollections getUserListFeed(String userId) {
        return feedRepository.findByUserId(userId);
//                .stream()
//                .map(feedCollections -> FeedDto.builder()
//                        .id(feedCollections.getId())
//                        .userId(feedCollections.getUserId())
//                        .content(feedCollections.getContent())
//                        .hashtags(feedCollections.getHashtags())
//                        .likes(feedCollections.getLikes())
//                        .createdDate(feedCollections.getCreatedDate())
//                        .modifiedDate(feedCollections.getLastModifiedDate())
//                        .build()).collect(Collectors.toList());
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
