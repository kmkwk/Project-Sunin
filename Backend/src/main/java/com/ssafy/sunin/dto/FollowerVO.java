package com.ssafy.sunin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FollowerVO {
    private Long userId;
    private Long followerMember;
}
