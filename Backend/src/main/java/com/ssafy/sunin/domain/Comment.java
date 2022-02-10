package com.ssafy.sunin.domain;

import com.ssafy.sunin.domain.user.User;
import com.ssafy.sunin.dto.comment.CommentReply;
import com.ssafy.sunin.dto.comment.CommentWrite;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Document(collection = "comments")
//@ApiModel(value = "댓글 (Comment)", description = "댓글 관련 정보를 가진 Domain Class")
@NoArgsConstructor
@Getter
@ToString
public class Comment {

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

//    private CommentUser user;

    public static Comment commentWriter(CommentWrite commentWrite, ObjectId objectId){
        return Comment.builder()
                .writer(commentWrite.getWriter())
                .content(commentWrite.getContent())
                .deleted(false)
                .modified(false)
                .writeDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .likes(0)
                .group(objectId)
                .depth(0)
                .build();
    }

    public static Comment commentReply(CommentReply commentReply){
        return Comment.builder()
                .writer(commentReply.getWriter())
                .content(commentReply.getContent())
                .deleted(false)
                .modified(false)
                .writeDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .likes(0)
                .group(new ObjectId(commentReply.getCommentId()))
                .depth(0)
                .build();
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


    public static List<Comment> mapComment(List<Comment> comments, Map<Long,User> userMap){
        return comments.stream()
                .map(comment -> Comment.builder()
                        .writer(comment.getWriter())
                        .content(comment.getContent())
                        .likes(comment.getLikes())
                        .writeDate(comment.getWriteDate())
                        .modifiedDate(comment.getModifiedDate())
                        .modified(comment.isModified())
                        .deleted(comment.isDeleted())
                        .group(comment.getGroup())
                        .user(userMap.get(comment.getWriter()))
                        .build())
                .collect(Collectors.toList());
    }

    @Builder
    public Comment(String content, Long writer, int likes, LocalDateTime writeDate, LocalDateTime modifiedDate, boolean modified, boolean deleted, ObjectId group, int depth, User user) {
        this.content = content;
        this.writer = writer;
        this.likes = likes;
        this.writeDate = writeDate;
        this.modifiedDate = modifiedDate;
        this.modified = modified;
        this.deleted = deleted;
        this.group = group;
        this.depth = depth;
        this.user = Comment.CommentUser.fromUser(user);
    }


    @Data
    static class CommentUser {
        private Long userId;
        private String nickName;
        private String image;

        private CommentUser(Long userId, String nickName, String image) {
            this.userId = userId;
            this.nickName = nickName;
            this.image = image;
        }

        public static CommentUser fromUser(User user) {
            return new CommentUser(user.getUserSeq(), user.getUserNickname(), user.getProfileImageUrl());
        }

    }
}
