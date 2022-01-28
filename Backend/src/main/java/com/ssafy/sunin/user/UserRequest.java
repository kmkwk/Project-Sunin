package com.ssafy.sunin.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class UserRequest {
    private String userEmail;
    private String userPassword;
    private String userName;
    private String userNickname;
    private String userTel;
    private String userAddress;
}
