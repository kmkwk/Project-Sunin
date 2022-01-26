package com.ssafy.sunin.repository;

import com.ssafy.sunin.domain.Follower;
import com.ssafy.sunin.dto.FollowerRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowerRepository extends JpaRepository<Follower,Long>, FollowerRepositoryCustom {

//    Long findByUserIdAndFollwerMember(FollowerRequest followerRequest);
//    List<Follower> findByFollowerMembers(Long userId);

}
