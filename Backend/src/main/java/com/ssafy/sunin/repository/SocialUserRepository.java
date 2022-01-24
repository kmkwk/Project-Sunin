package com.ssafy.sunin.repository;

import com.ssafy.sunin.domain.user.SocialUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface SocialUserRepository extends JpaRepository<SocialUser, Long> {
    // 소셜로그인 user의 crud를 담당
    Optional<SocialUser> findByUserEmail(String email);//소셜 로그인으로 반환되는 값 중 email을 통해 이미 생성된 사용자 or 처음 가입하는 사용자인지 판단
}