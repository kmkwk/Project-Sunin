package com.ssafy.sunin.service;

import com.ssafy.sunin.domain.FeedCollections;
import com.ssafy.sunin.dto.FeedDto;
import com.ssafy.sunin.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService{

    private final FeedRepository feedRepository;
    private final MongoTemplate mongoTemplate;

//    @Override
//    public String writeFeed(MultipartFile files, FeedCollections feedCollections) {
//        try {
//            String separator = File.separator;
//            String day = new SimpleDateFormat("yyMMdd").format(new Date());
//
//            File file = new File("");
//            String rootPath = file.getAbsolutePath().split("src")[0];
//            String savePath = rootPath+separator+"profileImg"+separator+day;
//
//            if(!new File(savePath).exists()){
//                try{
//                    new File(savePath).mkdirs();
//                } catch(Exception e){
//                    e.printStackTrace();
//                }
//            }
//
//            String originFileName = files.getOriginalFilename();
//            String saveFileName = UUID.randomUUID().toString()
//                    + originFileName.substring(originFileName.lastIndexOf("."));
//
//            String filePath = savePath+separator+saveFileName;
//            files.transferTo(new File(filePath));
//            System.out.println(files);
//
//            FeedCollections feedDto = FeedCollections.builder()
//                    .userId(feedCollections.getUserId())
//                    .content(feedCollections.getContent())
//                    .hashtags(feedCollections.getHashtags())
//                    .likes(feedCollections.getLikes())
//                    .fileName(saveFileName)
//                    .filePath(filePath)
//                    .build();
//
//            feedRepository.save(feedDto);
//
//        } catch (IOException e){
//            e.printStackTrace();
//            return null;
//        }

//        FeedCollections feedDto = FeedCollections.builder()
//                .userId(feedCollections.getUserId())
//                .content(feedCollections.getContent())
//                .hashtags(feedCollections.getHashtags())
//                .likes(feedCollections.getLikes())
//                .build();
//
//        feedRepository.save(feedDto);
//
//        return feedCollections.getId();
//    }

    @Override
    public String writeFeed(FeedCollections feedCollections) {
        feedRepository.save(feedCollections);
        return null;
    }

    @Override
    public List<FeedDto> listFeed() {
        System.out.println(feedRepository.findAll());
        return feedRepository.findAll()
                .stream()
                .map(feedCollections -> FeedDto.builder()
                        .id(feedCollections.get_id())
                        .userId(feedCollections.getUserId())
                        .content(feedCollections.getContent())
                        .hashtags(feedCollections.getHashtags())
                        .likes(feedCollections.getLikes())
                        .build()).collect(Collectors.toList());
    }

    @Override
    public FeedDto getFeed(String id) {
        Optional<FeedCollections> list = feedRepository.findById(id);
        FeedCollections feedCollections = list.get();

        return FeedDto.builder()
                .id(feedCollections.get_id())
                .userId(feedCollections.getUserId())
                .hashtags(feedCollections.getHashtags())
                .likes(feedCollections.getLikes())
//                .createdDate(feedCollections.getCreatedDate())
//                .modifiedDate(feedCollections.getLastModifiedDate())
                .build();
    }

    @Override
    public FeedDto updateFeed(FeedCollections feedCollections) {
        Query query = new Query(Criteria.where("id").is(feedCollections.get_id()));
        Update update = new Update();
        update.set("content",feedCollections.getContent());
        update.set("hashtags",feedCollections.getHashtags());
        mongoTemplate.updateFirst(query, update, "FeedCollections");
        return FeedDto.builder()
                .id(feedCollections.get_id())
                .userId(feedCollections.getUserId())
                .hashtags(feedCollections.getHashtags())
                .likes(feedCollections.getLikes())
//                .createdDate(feedCollections.getCreatedDate())
//                .modifiedDate(feedCollections.getLastModifiedDate())
                .build();
    }

    @Override
    public void deleteFeed(String id) {
        feedRepository.deleteById(id);
    }

    @Override
    public FeedDto userFeed(String userId) {
        return null;
    }
}
