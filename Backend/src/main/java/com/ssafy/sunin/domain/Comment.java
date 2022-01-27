package com.ssafy.sunin.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Document(collection = "comments")
@ApiModel(value = "댓글 (Comment)", description = "댓글 관련 정보를 가진 Domain Class")
@NoArgsConstructor
@Getter
public class Comment {

    @Id
    private ObjectId id;

    @Field("comment_feed_id")
    @ApiModelProperty(value = "댓글이 작성된 피드의 ObjectId")
    private ObjectId feedId;

    @Field("comment_content")
    @ApiModelProperty(value = "내용")
    private String content;

    @Field("comment_writer")
    @ApiModelProperty(value = "작성자")
    private String writer;

    @Field("comment_like")
    @ApiModelProperty(value = "좋아요")
    private int likes;

    @Field("comment_write_date")
    @ApiModelProperty(value = "작성일자")
    @CreatedDate
    private LocalDateTime writeDate;

    @Field("comment_modified_date")
    @ApiModelProperty(value = "수정일자")
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @Field("comment_modified")
    @ApiModelProperty(value = "수정여부")
    private boolean modified;

    @Field("comment_deleted")
    @ApiModelProperty(value = "삭제여부")
    private boolean deleted;

    @Field("comment_group")
    @ApiModelProperty(value = "대댓글 - 루트댓글")
    private ObjectId group;

    @Field("comment_order")
    @ApiModelProperty(value = "대댓글 - 깊이")
    private int depth;

    @Builder
    public Comment(ObjectId feedId, String content, String writer) {
        this.feedId = feedId;
        this.content = content;
        this.writer = writer;
        this.likes = 0;
        this.writeDate = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        this.modifiedDate = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        this.modified = false;
        this.deleted = false;
        this.group = id;
        this.depth = 0;
    }

    /*
    * 댓글 수정 메서드
    * */
    public void setCommentModified(String content) {
        this.content = content;
        this.modified = true;
        this.modifiedDate = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    }

    /*
    * 댓글 삭제 메서드
    * */
    public void setCommentDeleted() {
        this.deleted = true;
    }

    /*
    * 대댓글 작성 메서드
    * */
    public void setCommentGroup(ObjectId group) {
        this.group = group;
        this.depth++;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", feedId=" + feedId +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", likes=" + likes +
                ", writeDate=" + writeDate +
                ", modifiedDate=" + modifiedDate +
                ", modified=" + modified +
                ", deleted=" + deleted +
                ", group=" + group +
                ", depth=" + depth +
                '}';
    }
}