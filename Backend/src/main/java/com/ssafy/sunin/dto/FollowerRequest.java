package com.ssafy.sunin.dto;

import com.ssafy.sunin.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FollowerRequest {
    private Long userId;
    private Long followerMember;
}
