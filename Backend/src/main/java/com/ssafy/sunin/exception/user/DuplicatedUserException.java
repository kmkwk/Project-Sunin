package com.ssafy.sunin.exception.user;

import org.springframework.http.HttpStatus;

public class DuplicatedUserException extends UserException {

    private static final String MESSAGE = "중복되는 아이디가 존재합니다.";
    private static final String CODE = "SIGNUP-400";

    public DuplicatedUserException() {
        super(CODE, HttpStatus.BAD_REQUEST, MESSAGE);
    }
}
