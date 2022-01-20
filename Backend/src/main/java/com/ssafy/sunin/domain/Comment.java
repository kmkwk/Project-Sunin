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

@Document(collection = "comment")
@ApiModel(value = "댓글 (Comment)", description = "댓글 관련 정보를 가진 Domain Class")
@Getter
public class Comment {

    /*
    * _id               // PK
    * comment_feed_id   // 댓글이 달린 피드의 ID
    * comment_content   // 댓글 내용
    * comment_writer    // 댓글 작성자
    * comment_like      // 댓글에 달린 좋아요
    * comment_write_date    // 댓글 작성 날짜
    * comment_updated       // 댓글 수정 여부
    * */

    @Id
    private ObjectId id;

    @Field("comment_feed_id")
    @ApiModelProperty(value = "댓글이 작성된 피드의 ObjectId (int -> ObjectId 변경 예정)")
    private int feedId;
    // 후에 ObjectID로 받아와야함

    @Field("comment_content")
    @ApiModelProperty(value = "내용")
    private String content;

    @Field("comment_writer")
    @ApiModelProperty(value = "작성자")
    private String writer;

    @Field("comment_like")
    @ApiModelProperty(value = "좋아요 갯수")
    private int likes;
//    // 단순히 갯수 카운트가 아닌 누가 좋아요 눌렀는지 확인하고자..
//    private ObjectId[] likes;

    @Field("comment_write_date")
    @ApiModelProperty(value = "작성날짜")
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private Date date;

    @Field("comment_updated")
    @ApiModelProperty(value = "수정여부")
    private boolean updated;

    @Builder
    public Comment(int feedId, String content, String writer) {
        this.feedId = feedId;
        this.content = content;
        this.writer = writer;
        this.likes = 0;
        this.date = new Date();
        this.updated = false;
    }

}
