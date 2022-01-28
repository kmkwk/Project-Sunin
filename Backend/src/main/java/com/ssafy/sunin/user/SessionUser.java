package com.ssafy.sunin.user;



import com.ssafy.sunin.domain.user.User;
import lombok.Getter;

import java.io.Serializable;


@Getter
public class SessionUser implements Serializable {
    //유저 정보를 세션에 등록하기 위한 Dto 클래스로 직렬화 객체이다.
    //인증이 완료된 사용자 정보만 저장한다.

    private String id;
    private String name;

    public SessionUser(User user) {
        this.id = user.getUserId();
        this.name = user.getUserName();
    }
}