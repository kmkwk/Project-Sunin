package com.ssafy.sunin.repository.querydsl;

import com.ssafy.sunin.domain.FeedCollections;

import java.util.List;

public interface FeedRepositoryCustom {
    List<FeedCollections> getFollowerFeed(List<Long> followList);
}
