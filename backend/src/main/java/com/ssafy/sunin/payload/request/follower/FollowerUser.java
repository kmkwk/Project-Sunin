package com.ssafy.sunin.payload.request.follower;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowerUser {
    @NotNull
    private Long userId;
    @NotNull
    private Long followerMember;
}