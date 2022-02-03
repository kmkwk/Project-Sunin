package com.ssafy.sunin.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum Role {
    //사용자의 권한을 관리할 Enum클래스
    //시큐리티에서는 권한 코드 앞에 ROLE이 항상 있어야한다.

    USER("USER", "일반 사용자"),
    ADMIN("ADMIN", "관리자");

    private final String key;
    private final String title;

}