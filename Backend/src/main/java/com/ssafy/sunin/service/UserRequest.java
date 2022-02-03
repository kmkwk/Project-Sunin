package com.ssafy.sunin.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class UserRequest {
    private String userId;
    private String user_password;
    private String user_name;
    private String user_nickname;
    private String user_tel;
    private String user_address;
}
