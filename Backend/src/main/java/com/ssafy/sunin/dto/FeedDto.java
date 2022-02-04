package com.ssafy.sunin.dto;

import com.ssafy.sunin.domain.Comment;
import com.ssafy.sunin.domain.FeedCollections;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
@ToString
public class FeedDto {
    private String id;
    private String userId;
    private String content;
    private List<String> hashtags;
    private int likes;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private List<String> filePath;
    private Map<String,Object> likeUser;
    private List<Comment> comments;

    public static FeedDto feedDto(FeedCollections feed) {
        return FeedDto.builder()
                .id(feed.getId().toString())
                .userId(feed.getUserId())
                .content(feed.getContent())
                .hashtags(feed.getHashtags())
                .likes(feed.getLikes())
                .createdDate(feed.getCreatedDate())
                .modifiedDate(feed.getModifiedDate())
                .likeUser(feed.getLikeUser())
                .filePath(feed.getFilePath())
                .build();
    }

    @Builder
    public FeedDto(String id, String userId, String content, List<String> hashtags, int likes, LocalDateTime createdDate, LocalDateTime modifiedDate, List<String> filePath, Map<String, Object> likeUser, List<Comment> comments) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.hashtags = hashtags;
        this.likes = likes;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.filePath = filePath;
        this.likeUser = likeUser;
        this.comments = comments;
    }
}