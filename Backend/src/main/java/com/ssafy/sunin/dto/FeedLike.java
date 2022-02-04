package com.ssafy.sunin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class FeedLike {
    @NotBlank
    private String id;
    @NotBlank
    private String userId;
}
