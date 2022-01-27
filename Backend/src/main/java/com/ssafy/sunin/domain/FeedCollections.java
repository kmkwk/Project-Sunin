package com.ssafy.sunin.domain;

import io.swagger.annotations.ApiModel;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Document("feed")
@ApiModel(value = "피드", description = "피드 정보 클래스")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedCollections{

    @Id
    @Column(name = "_id")
    private ObjectId id;

    @Column(name = "userId")
    private String userId;

    private String content;

    private List<String> hashtags = new ArrayList<>();

    @Column(name = "likes")
    private int likes;

    @NotNull
    private Map<String,Object> likeUser;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    private List<String> filePath;

    private boolean flag;

    @Builder
    public FeedCollections(String userId, String content, List<String> hashtags, int likes, LocalDateTime createdDate, LocalDateTime lastModifiedDate, List<String> filePath, boolean flag) {
        this.userId = userId;
        this.content = content;
        this.hashtags = hashtags;
        this.likes = likes;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.filePath = filePath;
        this.flag = flag;
    }

    @Builder
    public FeedCollections(ObjectId id, String userId, String content, List<String> hashtags, int likes) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.hashtags = hashtags;
        this.likes = likes;
    }
}