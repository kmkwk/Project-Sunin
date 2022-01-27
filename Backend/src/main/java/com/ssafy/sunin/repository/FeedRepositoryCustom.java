package com.ssafy.sunin.repository;

import com.ssafy.sunin.domain.FeedCollections;
import com.ssafy.sunin.dto.FeedList;

import java.util.List;

public interface FeedRepositoryCustom {
    List<FeedCollections> getFollowerFeed(List<String> followList);
    List<FeedCollections> getLatestFeed(FeedList feedList);
    List<FeedCollections> getLikeFeed(FeedList feedList);
}
