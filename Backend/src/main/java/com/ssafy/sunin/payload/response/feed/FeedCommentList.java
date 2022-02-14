package com.ssafy.sunin.payload.response.feed;

import com.ssafy.sunin.domain.user.User;
import com.ssafy.sunin.payload.response.comment.CommentDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class FeedCommentList {

    private String id;

    @ApiModelProperty(value = "내용")
    private String content;

    @ApiModelProperty(value = "작성자")
    private Long writer;

    @ApiModelProperty(value = "좋아요")
    private int likes = 0;

    @ApiModelProperty(value = "작성일자")
    @CreatedDate
    private LocalDateTime writeDate = LocalDateTime.now();

    @ApiModelProperty(value = "수정일자")
    @LastModifiedDate
    private LocalDateTime modifiedDate = LocalDateTime.now();

    @ApiModelProperty(value = "수정여부")
    private boolean modified;

    @ApiModelProperty(value = "삭제여부")
    private boolean deleted;

    @ApiModelProperty(value = "대댓글 - 루트댓글")
    private ObjectId group;

    @ApiModelProperty(value = "대댓글 - 깊이")
    private int depth;


}