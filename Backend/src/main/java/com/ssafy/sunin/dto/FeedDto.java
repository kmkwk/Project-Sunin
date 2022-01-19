package com.ssafy.sunin.dto;

import com.ssafy.sunin.domain.FeedCollections;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@ToString
public class FeedDto {

    private String _id;
    private String userId;
    private String content;
    private List<String> hashtags = new ArrayList<>();
    private int likes;
    private LocalDateTime createdDate = LocalDateTime.now();
    private LocalDateTime modifiedDate = LocalDateTime.now();
    private String fileName;
    private String filePath;
    private Long fileSize;

    @Builder
    public FeedDto(String id, String userId, String content, List<String> hashtags, int likes, LocalDateTime createdDate, LocalDateTime modifiedDate, String fileName, String filePath, Long fileSize) {
        this._id = id;
        this.userId = userId;
        this.content = content;
        this.hashtags = hashtags;
        this.likes = likes;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

    @Builder
    public FeedDto(String _id, String userId, String content, List<String> hashtags, int likes, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this._id = _id;
        this.userId = userId;
        this.content = content;
        this.hashtags = hashtags;
        this.likes = likes;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}