package com.ssafy.sunin.repository.querydsl;

import com.ssafy.sunin.domain.FeedCollections;
import com.ssafy.sunin.domain.QFeedCollections;
import com.ssafy.sunin.domain.user.QUser;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.support.QuerydslRepositorySupport;
import java.util.*;

public class FeedRepositoryImpl extends QuerydslRepositorySupport implements FeedRepositoryCustom {

    private static final QFeedCollections qfeed = QFeedCollections.feedCollections;
    private static final QUser qUser = QUser.user;

    public FeedRepositoryImpl(MongoOperations operations){
        super(operations);
    }

//    @Override
//    public List<FeedCollections> getFollowerFeed(List<Long> followList) {
//        return from(qfeed)
//                .where(qfeed.userId.in(followList).and(qfeed.flag.eq(true)))
//                .orderBy(qfeed.createdDate.desc())
//                .fetch();
//    }
    @Override
    public List<FeedCollections> getFollowerFeed(List<Long> followList) {
        return from(qfeed)
                .where(qfeed.userId.in(followList).and(qfeed.flag.eq(true)))
                .orderBy(qfeed.createdDate.desc())
                .fetch();
    }
}