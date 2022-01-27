package com.ssafy.sunin.service;

import com.ssafy.sunin.dto.FollowerRequest;

public interface FollowerService {
    // Todo : 팔로워 추가
    Long addFollower(FollowerRequest followerRequest);
    // Todo : 팔로워 삭제
    void deleteFollower(FollowerRequest followerRequest);
    // Todo : 팔로워 카운트
    Long countFollower(Long id);
    // Todo : 팔로잉 카운트
    Long countFollowing(Long followerMember);
}
