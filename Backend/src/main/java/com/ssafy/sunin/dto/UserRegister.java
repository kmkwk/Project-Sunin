package com.ssafy.sunin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@ToString
@ApiModel(value = "UserRegisterDto : 회원가입시 필요한 회원 정보", description = "회원가입시 필요한 회원 정보를 나타낸다.")
public class UserRegister {
    @NotNull
    @Size(min = 3, max = 20)
    private String email;

    @NotNull
    @Size(min = 3, max = 100)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotNull
    @Size(min = 3, max = 15)
    private String username;

    @NotNull
    @Size(max = 45)
    private String userNickname;

    @NotNull
    @Size(max = 45)
    private String userTel;

    @NotNull
    @Size(max = 45)
    private String useraddress;

}
