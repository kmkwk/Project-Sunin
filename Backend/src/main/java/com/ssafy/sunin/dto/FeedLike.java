package com.ssafy.sunin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FeedLike {
    private String id;
    private int likes;
    private String user;
}
