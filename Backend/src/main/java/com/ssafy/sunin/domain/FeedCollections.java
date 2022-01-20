package com.ssafy.sunin.domain;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document("feed")
@ApiModel(value = "피드", description = "피드 정보 클래스")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedCollections{

    @Id
    @Column(name = "_id")
    private String id;

    @Column(name = "userid")
    private String userId;

    private String content;

    private List<String> hashtags = new ArrayList<>();

    @Column(name = "likes")
    private int likes;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

//    private String fileName;
//
//    private String filePath;
//
//    private Long fileSize;

    private boolean flag;

    @Builder
    public FeedCollections(String id, String userId, String content, List<String> hashtags, int likes) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.hashtags = hashtags;
        this.likes = likes;
    }
}