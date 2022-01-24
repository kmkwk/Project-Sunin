package com.ssafy.sunin.repository;

import com.ssafy.sunin.domain.FeedCollections;
import com.ssafy.sunin.domain.QFeedCollections;
import com.ssafy.sunin.dto.FeedDto;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.mongodb.core.MongoOperations;
import java.util.List;
import java.util.Map;

public class FeedRepositoryImpl extends QuerydslRepositorySupport implements FeedRepositoryWrapper {

    private static final QFeedCollections feedCollection = QFeedCollections.feedCollections;

    public FeedRepositoryImpl(MongoOperations operations){
        super(operations.getClass());
    }

    @Override
    public FeedDto updateFeed(FeedCollections feedCollections) {
        return null;
    }

    @Override
    public void deleteFeed(String id) {

    }

    @Override
    public List<FeedDto> getRecentFeed(String userId) {
        return null;
    }

    @Override
    public List<FeedDto> getFollowerFeed(String userId) {
        return null;
    }

    @Override
    public List<FeedDto> getSelectBoxFeed(Map<String, Object> map) {
        return null;
    }

    @Override
    public List<FeedDto> getRecentLikeFeed(FeedCollections feedCollections) {
        return null;
    }

    @Override
    public List<FeedDto> getUserFeed(FeedCollections feedCollections) {
        return null;
    }

    @Override
    public FeedCollections findByUserId(String userId) {
        return from(feedCollection).where(feedCollection.userId.eq(userId)).fetchOne();
    }
}
