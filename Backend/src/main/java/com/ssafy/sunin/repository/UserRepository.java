package com.ssafy.sunin.repository;

import com.ssafy.sunin.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    //소셜로그인user의 crud를 담당

    Optional<User> findByUserEmail(String email);//소셜 로그인으로 반환되는 값 중 email을 통해 이미 생성된 사용자 or 처음 가입하는 사용자인지 판단
}