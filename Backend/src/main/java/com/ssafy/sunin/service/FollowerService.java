package com.ssafy.sunin.service;

import com.ssafy.sunin.payload.request.follower.FollowerUser;

public interface FollowerService {

    Long addFollower(FollowerUser followerUser);

    void deleteFollower(FollowerUser followerUser);

    int countFollower(Long userId);

    int countFollowing(Long followerMember);
}
