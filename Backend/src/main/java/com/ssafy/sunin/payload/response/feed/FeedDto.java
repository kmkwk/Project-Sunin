package com.ssafy.sunin.payload.response.feed;

import com.ssafy.sunin.domain.Comment;
import com.ssafy.sunin.domain.FeedCollections;
import com.ssafy.sunin.domain.user.User;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public static List<FeedDto> mapFeedDto(List<FeedCollections> feedCollection, Map<Long,User> userMap){
        return feedCollection.stream()
                .map(feedCollections -> FeedDto.builder()
                        .id(feedCollections.getId().toString())
                        .content(feedCollections.getContent())
                        .hashtags(feedCollections.getHashtags())
                        .likes(feedCollections.getLikes())
                        .createdDate(feedCollections.getCreatedDate())
                        .modifiedDate(feedCollections.getModifiedDate())
                        .likeUser(feedCollections.getLikeUser())
                        .filePath(feedCollections.getFilePath())
                        .user(userMap.get(feedCollections.getUserId()))
                        .build())
                .collect(Collectors.toList());
    }

    public static List<FeedDto> personFeedDto(List<FeedCollections> feedCollection,User user) {
        return feedCollection.stream()
                .map(feedCollections -> FeedDto.builder()
                        .id(feedCollections.getId().toString())
                        .content(feedCollections.getContent())
                        .hashtags(feedCollections.getHashtags())
                        .likes(feedCollections.getLikes())
                        .createdDate(feedCollections.getCreatedDate())
                        .modifiedDate(feedCollections.getModifiedDate())
                        .likeUser(feedCollections.getLikeUser())
                        .filePath(feedCollections.getFilePath())
                        .user(user)
                        .build())
                .collect(Collectors.toList());
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