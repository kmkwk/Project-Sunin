package com.ssafy.sunin.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class CommentLike {
    @NotBlank
    private String feedId;
    @NotBlank
    private String commentId;
    @NotNull
    private Long userId;
}