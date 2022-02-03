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
@ApiModel(value = "UserLoginDto : 로그인시 필요한 회원 정보", description = "로그인 시 필요한 회원 정보인 id, password를 나타낸다.")
public class UserLogin {
    @NotNull
    @Size(min = 3, max = 20)
    private String email;

    @NotNull
    @Size(min = 3, max = 100)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
