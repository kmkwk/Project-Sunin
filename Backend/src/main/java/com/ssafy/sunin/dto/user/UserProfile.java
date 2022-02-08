package com.ssafy.sunin.dto.user;

import com.ssafy.sunin.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserProfile {
    private Long id;
    private String nickName;
    private String image;

    public static UserProfile userProfile(User user) {
        return UserProfile.builder()
                .id(user.getUserSeq())
                .nickName(user.getUserNickname())
                .image(user.getProfileImageUrl())
                .build();
       }

    @Builder
    public UserProfile(Long id, String nickName, String image) {
        this.id = id;
        this.nickName = nickName;
        this.image = image;
    }
}
