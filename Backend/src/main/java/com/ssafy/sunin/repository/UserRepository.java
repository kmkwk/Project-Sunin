package com.ssafy.sunin.repository;

import com.ssafy.sunin.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    Optional<User> findByUserId(String userId);
    User findByUserSeq(Long followerMember);

    User findByUserNickname(String userNickname);

    User findByUserId(String userId);

    User findProfileByUserSeq(Long userSeq);
}