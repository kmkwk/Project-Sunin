package com.ssafy.sunin.service;

import com.ssafy.sunin.domain.FeedCollections;
import com.ssafy.sunin.dto.FeedDto;
import com.ssafy.sunin.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {

    private final FeedRepository feedRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public FeedDto writeFeedFile(MultipartFile files, FeedCollections feedCollections) {
        return null;
    }

    @Override
    public FeedDto writeFeed(FeedCollections feedCollections) {
        feedRepository.save(feedCollections);
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
}
