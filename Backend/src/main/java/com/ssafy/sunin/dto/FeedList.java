package com.ssafy.sunin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
public class FeedList {
    @NotBlank
    private String userId;
    private int size;
    private int pageNum;
}
