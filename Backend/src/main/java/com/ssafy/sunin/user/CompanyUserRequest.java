package com.ssafy.sunin.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CompanyUserRequest {
    private String companyId;
    private String companyPassword;
    private String companyName;
    private String companyNickname;
    private String companyTel;
    private String companyAddress;
    private boolean approval;
}
