package com.ssafy.sunin.repository.querydsl;

import com.ssafy.sunin.domain.Follower;
import com.ssafy.sunin.dto.follower.FollowerUser;
import java.util.List;

public interface FollowerRepositoryCustom {

    Long deleteFollower(Follower follower);

    List<Long> getFollowingList(Long userId);

    Long getFollowerCount(Long id);

    Long getFollowingCount(Long followerMember);

    Follower getUser(FollowerUser followerUser);
}
