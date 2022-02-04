package com.ssafy.sunin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class FollowerVO {
    @NotBlank
    private Long userId;
    @NotBlank
    private Long followerMember;
}
