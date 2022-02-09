package com.ssafy.sunin.dto.feed;

import com.ssafy.sunin.domain.Comment;
import com.ssafy.sunin.domain.FeedCollections;
import com.ssafy.sunin.dto.user.UserProfile;
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
    private String content;
    private List<String> hashtags;
    private int likes;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private List<String> filePath;
    private Map<Long,Object> likeUser;
    private List<Comment> comments;

    private Long userId;
    private String nickName;
    private String image;

    public static FeedDto feedDto(FeedCollections feed, UserProfile userProfile) {
        return FeedDto.builder()
                .id(feed.getId().toString())
                .content(feed.getContent())
                .hashtags(feed.getHashtags())
                .likes(feed.getLikes())
                .createdDate(feed.getCreatedDate())
                .modifiedDate(feed.getModifiedDate())
                .likeUser(feed.getLikeUser())
                .filePath(feed.getFilePath())
                .userId(userProfile.getId())
                .nickName(userProfile.getNickName())
                .image(userProfile.getImage())
                .comments(feed.getComments())
                .build();
    }

    @Builder
    public FeedDto(String id, String content, List<String> hashtags, int likes, LocalDateTime createdDate, LocalDateTime modifiedDate, List<String> filePath, Map<Long, Object> likeUser, List<Comment> comments, Long userId, String nickName, String image) {
        this.id = id;
        this.content = content;
        this.hashtags = hashtags;
        this.likes = likes;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.filePath = filePath;
        this.likeUser = likeUser;
        this.comments = comments;
        this.userId = userId;
        this.nickName = nickName;
        this.image = image;
    }
}