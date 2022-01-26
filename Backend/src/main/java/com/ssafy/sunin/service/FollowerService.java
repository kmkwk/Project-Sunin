package com.ssafy.sunin.service;

import com.ssafy.sunin.domain.Follower;
import com.ssafy.sunin.dto.FeedDto;
import com.ssafy.sunin.dto.FollowerRequest;

import java.util.List;

public interface FollowerService {
    // Todo : 팔로워 추가
    Long addFollower(FollowerRequest followerRequest);
    // Todo : 팔로워 삭제
    void deleteFollower(FollowerRequest followerRequest);
    // Todo : 팔로잉 조회 - 팔로잉 리스트 가져와서 팔로잉 유저 최신순 피드 가져오기
    List<Follower> getFollowerFeed(Long id);
    // Todo : 팔로워 카운트
    Long countFollower(Long id);
    // Todo : 팔로잉 카운트
    Long countFollowing(Long followerMember);
}
