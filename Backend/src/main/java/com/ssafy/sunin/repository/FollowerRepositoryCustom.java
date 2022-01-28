package com.ssafy.sunin.repository;

import com.ssafy.sunin.domain.user.User;
import java.util.List;

public interface FollowerRepositoryCustom {
    Long deleteFollower(User user, User followerMember);
    List<Long> getUser(User user, User followerMember);
    List<String> getFollowingList(Long id);
    Long getFollowerCount(Long id);
    Long getFollowingCount(Long followerMember);
}
