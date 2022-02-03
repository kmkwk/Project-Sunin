package com.ssafy.sunin.repository;

import com.ssafy.sunin.domain.Follower;
import com.ssafy.sunin.dto.FollowerVO;

import java.util.List;

public interface FollowerRepositoryCustom {
    Long deleteFollower(Follower follower);

    List<String> getFollowingList(Long id);

    Long getFollowerCount(Long id);

    Long getFollowingCount(Long followerMember);

    Follower getUser(FollowerVO followerVO);
}
