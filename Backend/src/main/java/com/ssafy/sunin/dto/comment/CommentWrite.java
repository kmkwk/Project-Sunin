package com.ssafy.sunin.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class CommentWrite {
    @NotNull
    private Long writer;
    private String content;
}
