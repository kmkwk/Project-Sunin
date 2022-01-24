package com.ssafy.sunin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FeedLikeResponse {
    private String id;
    private List<String> likeUser;
    private int likes;
}
