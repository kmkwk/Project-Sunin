package com.ssafy.sunin.domain.user;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Social {
    // 소셜로그인을 관리

    KAKAO("카카오"),
    NAVER("네이버"),
    GOOGLE("구글");

    private final String title;
}