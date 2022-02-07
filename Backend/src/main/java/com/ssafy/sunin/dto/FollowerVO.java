package com.ssafy.sunin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class FollowerVO {
    @NotNull
    private Long userId;
    @NotNull
    private Long followerMember;
}