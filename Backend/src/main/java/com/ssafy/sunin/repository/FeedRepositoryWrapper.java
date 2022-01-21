package com.ssafy.sunin.repository;

import com.ssafy.sunin.domain.FeedCollections;
import com.ssafy.sunin.dto.FeedDto;
import java.util.List;
import java.util.Map;

public interface FeedRepositoryWrapper {
    // 피드 수정
    FeedDto updateFeed(FeedCollections feedCollections);

    // 피드 삭제
    void deleteFeed(String id);

    // 최근 피드 조회
    List<FeedDto> getRecentFeed(String userId);

    // 나의 팔로워 피드 조회
    List<FeedDto> getFollowerFeed(String userId);

    // select box 피드 조회
    List<FeedDto> getSelectBoxFeed(Map<String,Object> map);

    // 최신순, 좋아요 피드 조회
    List<FeedDto> getRecentLikeFeed(FeedCollections feedCollections);

    // 유저, 기업별 피드 조회
    List<FeedDto> getUserFeed(FeedCollections feedCollections);

    FeedCollections findByUserId(String userId);
}
