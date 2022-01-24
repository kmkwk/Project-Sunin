package com.ssafy.sunin.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.util.Date;

@Document(collection = "comments")
@ApiModel(value = "댓글 (Comment)", description = "댓글 관련 정보를 가진 Domain Class")
@NoArgsConstructor
@Getter
public class Comment {

    @Id
    private ObjectId id;

    @Field("comment_feed_id")
    @ApiModelProperty(value = "댓글이 작성된 피드의 ObjectId (int -> ObjectId 변경 예정)")
    private int feedId;

    @Field("comment_content")
    @ApiModelProperty(value = "내용")
    private String content;

    @Field("comment_writer")
    @ApiModelProperty(value = "작성자")
    private String writer;

    @Field("comment_like")
    @ApiModelProperty(value = "좋아요 갯수")
    private int likes;

    @Field("comment_write_date")
    @ApiModelProperty(value = "작성날짜")
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private Date date;

    @Field("comment_updated")
    @ApiModelProperty(value = "수정여부")
    private boolean updated;

    @Field("comment_deleted")
    @ApiModelProperty(value = "수정여부")
    private boolean deleted;

    /*
     * 계층형 댓글
     * */
    @Field("comment_group")
    private ObjectId group; // 대그룹

    @Field("comment_order")
    private int order;  // 소그룹

    @Builder
    public Comment(int feedId, String content, String writer) {
        this.feedId = feedId;
        this.content = content;
        this.writer = writer;
        this.likes = 0;
        this.date = new Date();
        this.updated = false;

        // 계층형 댓글
        this.group = id;
        this.order = 1;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void setGroup(ObjectId group) {
        this.group = group;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", feedId=" + feedId +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", likes=" + likes +
                ", date=" + date +
                ", updated=" + updated +
                ", deleted=" + deleted +
                ", group=" + group +
                ", order=" + order +
                '}';
    }
}
