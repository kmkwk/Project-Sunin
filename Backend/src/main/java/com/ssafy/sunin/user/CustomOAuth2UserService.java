package com.ssafy.sunin.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Slf4j
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User>{
    // 소셜 로그인 이후 가져온 사용자의 정보들을 기반으로 가입 및 정보수정, 세션 저장 등의 기능 구현

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        //어떤 로그인 서비스인지 구분하는 코드로 구글, 네이버 등 구분하기 위해 사용 (ex) 구글 :google, 네이버 :naver)
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        //primary key를 의미, 구글은 기본 지원
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        // OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담을 클래스
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

//        User user = saveOrUpdate(attributes);

//        return new DefaultOAuth2User(//oauth2user를 리턴하는데 권한 등을 포함
//                Collections.singleton(new SimpleGrantedAuthority("@@@@@")),
//                attributes,
//                "@@@@@"
//        );
        return null;
    }

    //이메일을 키로 잡음
    //소셜로그인한 사용자 정보가 업데이트 되면 Member엔티티에도 반영
//    private User saveOrUpdate(OAuthAttributes attributes) {
//        User member = UserRepository.findByUserEmail(attributes.getEmail())
//                .map(entity -> entity.update(attributes.getName()))
//                .orElse(attributes.toEntity());
//        return UserRepository.save(member);
//    }
}