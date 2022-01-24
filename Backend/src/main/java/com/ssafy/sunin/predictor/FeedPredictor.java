package com.ssafy.sunin.predictor;

import com.querydsl.core.BooleanBuilder;
import com.ssafy.sunin.domain.QFeedCollections;
import org.springframework.util.ObjectUtils;

import java.util.function.Predicate;

public class FeedPredictor {

    private static final QFeedCollections feedCollections = QFeedCollections.feedCollections;
    private final BooleanBuilder builder = new BooleanBuilder();

    public FeedPredictor userId(String userId){
        if(!ObjectUtils.isEmpty(userId)){
            builder.and(feedCollections.userId.eq(userId));
        }
        return this;
    }

    public FeedPredictor id(String id){
        if(!ObjectUtils.isEmpty(id)){
            builder.and(feedCollections.userId.eq(id));
        }
        return this;
    }

    public Predicate values(){
        return (Predicate) builder.getValue();
    }
}
