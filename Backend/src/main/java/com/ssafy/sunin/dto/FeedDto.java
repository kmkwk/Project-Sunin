package com.ssafy.sunin.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDateTime;
import java.util.List;

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
    private String fileName;
    private List<String> filePath;
    private Long fileSize;

    @Builder
    public FeedDto(String id, String userId, String content, List<String> hashtags, int likes, LocalDateTime createdDate, LocalDateTime modifiedDate, List<String> filePath) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.hashtags = hashtags;
        this.likes = likes;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.filePath = filePath;
    }

    @Builder
    public FeedDto(String id, String userId, String content, List<String> hashtags, int likes, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.hashtags = hashtags;
        this.likes = likes;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}