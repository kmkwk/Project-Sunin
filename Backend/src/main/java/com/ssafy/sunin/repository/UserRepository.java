package com.ssafy.sunin.repository;

import com.ssafy.sunin.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    Optional<User> findByUserId(String userId);
    User findFollowerByUserSeq(Long followerMember);

    User findByUserId(String userId);

    User findProfileByUserSeq(Long userSeq);

    List<User> findFollowerListByUserSeqIn(List<Long> userId);
}