package com.ssafy.sunin.dto.feed;

import com.ssafy.sunin.domain.Comment;
import com.ssafy.sunin.domain.FeedCollections;
import com.ssafy.sunin.domain.user.User;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
@ToString
public class FeedDto {
    private String id;
    private String content;
    private List<String> hashtags;
    private int likes;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private List<String> filePath;
    private Map<Long, Object> likeUser;
    private Map<Object, Comment> comments;
//    @JsonUnwrapped
    private FeedUserDto user;

    public static FeedDto feedDto(FeedCollections feed, User user) {
        return FeedDto.builder()
                .id(feed.getId().toString())
                .content(feed.getContent())
                .hashtags(feed.getHashtags())
                .likes(feed.getLikes())
                .createdDate(feed.getCreatedDate())
                .modifiedDate(feed.getModifiedDate())
                .likeUser(feed.getLikeUser())
                .filePath(feed.getFilePath())
                .user(user)
                .comments(feed.getComments())
                .build();
    }

    @Builder
    public FeedDto(String id, String content, List<String> hashtags, int likes, LocalDateTime createdDate, LocalDateTime modifiedDate, List<String> filePath, Map<Long, Object> likeUser, Map<Object, Comment> comments, User user) {
        this.id = id;
        this.content = content;
        this.hashtags = hashtags;
        this.likes = likes;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.filePath = filePath;
        this.likeUser = likeUser;
        this.comments = comments;
        this.user = FeedUserDto.fromUser(user);
    }

    @Data
    static class FeedUserDto {
        private Long userId;
        private String nickName;
        private String image;

        private FeedUserDto(Long userId, String nickName, String image) {
            this.userId = userId;
            this.nickName = nickName;
            this.image = image;
        }

        public static FeedUserDto fromUser(User user) {
            return new FeedUserDto(user.getUserSeq(), user.getUserNickname(), user.getProfileImageUrl());
        }

    }

}