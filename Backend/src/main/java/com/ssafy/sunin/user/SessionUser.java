package com.ssafy.sunin.user;



import com.ssafy.sunin.domain.user.User;
import lombok.Getter;

import java.io.Serializable;


@Getter
public class SessionUser implements Serializable {
    //유저 정보를 세션에 등록하기 위한 직렬화 객체
    //인증이 완료된 사용자 정보만 저장

    private String name;
    private String email;

    public SessionUser(User user) {
        this.name = user.getUsername();
        this.email = user.getUserEmail();
    }
}