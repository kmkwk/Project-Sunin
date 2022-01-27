package com.ssafy.sunin.repository;

import com.ssafy.sunin.domain.Follower;
import com.ssafy.sunin.domain.user.User;
import com.ssafy.sunin.dto.FollowerRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowerRepository extends JpaRepository<Follower,Long>, FollowerRepositoryCustom {
//    void deleteByUserAndFollowerMember(User user, Long FollowerMember);
}
