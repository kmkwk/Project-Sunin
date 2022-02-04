package com.ssafy.sunin.domain;

import com.ssafy.sunin.dto.FeedDto;
import com.ssafy.sunin.dto.FeedUpdate;
import io.swagger.annotations.ApiModel;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

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
    private ObjectId id;

    private String userId;

    private String content;

    private List<String> hashtags = new ArrayList<>();

    private int likes;

    @NotNull
    private Map<String,Object> likeUser;

    @CreatedDate
    private LocalDateTime createdDate = LocalDateTime.now();

    @LastModifiedDate
    private LocalDateTime modifiedDate = LocalDateTime.now();

    private List<String> filePath = new ArrayList<>();

    private boolean flag = false;

    private List<Comment> comments = new ArrayList<>();

    /*
      피드 수정
     */
    public void setFeedModified(FeedUpdate feedUpdate){
        this.content = feedUpdate.getContent();;
        this.hashtags = feedUpdate.getHashtags();
        this.modifiedDate = LocalDateTime.now();
    };

    /*
        피드 삭제
     */
    public void setFeedDelete(){
        this.flag = false;
        this.modifiedDate = LocalDateTime.now();
    }

    /*
        피드 좋아요
     */
    public void setLikeModified(int likes,Map<String, Object> likeUser){
        this.likes = likes;
        this.likeUser = likeUser;
    }

    @Builder
    public FeedCollections(ObjectId id, String userId, String content, List<String> hashtags, int likes, Map<String, Object> likeUser, LocalDateTime createdDate, LocalDateTime modifiedDate, List<String> filePath, boolean flag, List<Comment> comments) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.hashtags = hashtags;
        this.likes = likes;
        this.likeUser = likeUser;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.filePath = filePath;
        this.flag = flag;
        this.comments = comments;
    }
}