package com.ssafy.sunin.service;

import com.ssafy.sunin.dto.UserRegister;

public interface UserService {
    public void register(UserRegister userRegisterDto) throws Exception;
}
