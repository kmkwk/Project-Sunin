package com.ssafy.sunin.service;

import com.ssafy.sunin.payload.response.feed.FeedSearch;
import com.ssafy.sunin.payload.response.user.UserDetailProfile;
import com.ssafy.sunin.payload.request.feed.*;
import com.ssafy.sunin.payload.response.feed.FeedCommentDto;
import com.ssafy.sunin.payload.response.feed.FeedDto;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Set;

public interface FeedService {
    FeedDto updateFile(FileUpdate fileUpdate);

    FeedDto writeImageFeed(FeedWrite feedWrite);

    List<String> downloadFileFeed(String fileNames);

    FeedCommentDto getDetailFeed(String id);

    FeedDto updateFeed(FeedUpdate feedUpdate);

    FeedDto addFile(FeedFile feedFile);

    void deleteFeed(String id, Long userId);

    List<FeedDto> getFollowerLatestFeed(Long userId);

    List<FeedDto> getFollowerLikeFeed(Long userId);

    List<FeedDto> getPersonalFeed(Long userId);

    List<FeedDto> getLatestFeed(Pageable pageable);

    List<FeedDto> getLikeFeed(Pageable pageable);

    void likeFeed(FeedLike feedLike);

    List<UserDetailProfile> getLikeUserList(String id);

    Long feedCount(Long userId);

    FeedSearch getSearchList(Pageable pageable,String content);

}