package com.ssafy.sunin.security.dto;


import com.ssafy.sunin.domain.user.Role;
import com.ssafy.sunin.domain.user.Social;
import com.ssafy.sunin.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
@Getter
@ToString
public class OAuthAttributes {
    //스프링 시큐리티 OAuth 인증을 위한 속성 객체

    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;
    private String registrationId; //소셜 타입 네이버, 구글 등

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture, String registrationId){
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.registrationId = registrationId;
    }

    //인증 객체를 리턴한다.
    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        log.info("요청 :: "+registrationId); //소셜 타입 ex)네이버
        log.info("유저이름 :: "+userNameAttributeName); //Principal.getName 하게 되면 나오는 로그인한 유저의 이름으로 등록할 필드명
        log.info("속성 :: "+attributes); // 플랫폼에서 반환받은 유저 정보
        switch(registrationId){
            case "naver":
                return ofNaver(registrationId, "name", attributes);
            case "kakao":
                return ofKakao(registrationId, "nickname", attributes);
            case "google":
                return ofGoogle(registrationId, "name", attributes);
            default:
                throw new IllegalArgumentException("해당 로그인을 찾을 수 없습니다.");
        }
    }
    private static OAuthAttributes ofKakao(String registrationId, String userNameAttributeName, Map<String, Object> attributes){
        Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
        properties.put("id", attributes.get("id"));
        return OAuthAttributes.builder()
                .name((String) properties.get("nickname"))
                .email((String) properties.get("email"))
                .picture((String) properties.get("profile_image"))
                .attributes(properties)
                .nameAttributeKey(userNameAttributeName)
                .registrationId(registrationId)
                .build();
    }
    private static OAuthAttributes ofGoogle(String registrationId, String userNameAttributeName, Map<String, Object> attributes){
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .registrationId(registrationId)
                .build();
    }
    private static OAuthAttributes ofNaver(String registrationId, String userNameAttributeName, Map<String, Object> attributes){
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .picture((String) response.get("profileImage"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .registrationId(registrationId)
                .build();
    }
    public User toEntity(){
        return User.builder().userName(name).userEmail(email).userPicture(picture).role(Role.USER).social(Social.valueOf(registrationId.toUpperCase())).build();
    }
}