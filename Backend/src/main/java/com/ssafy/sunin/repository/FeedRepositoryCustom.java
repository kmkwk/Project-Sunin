package com.ssafy.sunin.repository;

import com.ssafy.sunin.domain.FeedCollections;
import com.ssafy.sunin.dto.FeedList;

import java.util.List;

public interface FeedRepositoryCustom {
    // Todo: 나의 팔로워 피드 조회
    List<FeedCollections> getFollowerFeed(List<String> followList);

    // Todo: 최근순 피드 조회
    List<FeedCollections> getLatestFeed(FeedList feedList);

    // Todo: 좋아요 피드 조회
    List<FeedCollections> getLikeFeed(FeedList feedList);
}
