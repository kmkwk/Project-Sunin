package com.ssafy.sunin.service;

import com.ssafy.sunin.dto.follower.FollowerUser;

public interface FollowerService {
    Long addFollower(FollowerUser followerUser);

    void deleteFollower(FollowerUser followerUser);

    Long countFollower(Long userId);

    Long countFollowing(Long followerMember);
}
