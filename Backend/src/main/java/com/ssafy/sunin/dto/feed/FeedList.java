package com.ssafy.sunin.dto.feed;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class FeedList {
    @NotNull
    private Long userId;
    private int size;
    private int pageNum;
}