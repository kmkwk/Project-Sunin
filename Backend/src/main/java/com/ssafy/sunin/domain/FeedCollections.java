package com.ssafy.sunin.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
//import javax.persistence.Column;
//import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Document("feed")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedCollections {

//    @Id
//    @Column(name = "_id")
    private String _id;

//    @Column(name = "userid")
    private String userId;

    private String content;

    private List<String> hashtags = new ArrayList<>();

//    @Column(name = "likes")
    private int likes;

    private String fileName;

    private String filePath;

    private Long fileSize;

    @Builder
    public FeedCollections(String _id, String userId, String content, List<String> hashtags, int likes, String fileName, String filePath, Long fileSize) {
        this._id = _id;
        this.userId = userId;
        this.content = content;
        this.hashtags = hashtags;
        this.likes = likes;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

    @Builder
    public FeedCollections(String _id, String userId, String content, List<String> hashtags, int likes) {
        this._id = _id;
        this.userId = userId;
        this.content = content;
        this.hashtags = hashtags;
        this.likes = likes;
    }

    public FeedCollections(String fileName, String filePath, Long fileSize) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }
}