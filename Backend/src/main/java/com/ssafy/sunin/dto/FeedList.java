package com.ssafy.sunin.dto;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FeedList {
    private String userId;
    private int size;
    private int pageNum;
}
