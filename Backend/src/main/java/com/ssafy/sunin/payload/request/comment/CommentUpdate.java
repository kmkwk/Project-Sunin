package com.ssafy.sunin.payload.request.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentUpdate {
    @NotBlank
    private String feedId;
    @NotBlank
    private String commentId;
    @NotNull
    private Long writer;
    private String content;
}
