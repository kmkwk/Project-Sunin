package com.ssafy.sunin.service;

import com.ssafy.sunin.dto.FollowerVO;

public interface FollowerService {
    Long addFollower(FollowerVO followerVO);

    void deleteFollower(FollowerVO followerVO);

    Long countFollower(Long id);

    Long countFollowing(Long followerMember);
}
