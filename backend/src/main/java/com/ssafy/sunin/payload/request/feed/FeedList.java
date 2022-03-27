package com.ssafy.sunin.payload.request.feed;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedList {
    @NotNull
    private Long userId;
    private int size;
    private int pageNum;
}