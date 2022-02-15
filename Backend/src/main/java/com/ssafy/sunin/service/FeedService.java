package com.ssafy.sunin.service;

import com.ssafy.sunin.domain.FeedCollections;
import com.ssafy.sunin.payload.response.feed.FeedSearch;
import com.ssafy.sunin.payload.response.user.UserDetailProfile;
import com.ssafy.sunin.payload.request.feed.*;
import com.ssafy.sunin.payload.response.feed.FeedCommentDto;
import com.ssafy.sunin.payload.response.feed.FeedDto;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface FeedService {
    FeedCollections updateFile(FileUpdate fileUpdate);

    FeedCollections writeImageFeed(FeedWrite feedWrite);

    List<String> downloadFileFeed(String fileNames);

    FeedCommentDto getDetailFeed(String id);

    FeedCollections updateFeed(FeedUpdate feedUpdate);

    FeedCollections addFile(FeedFile feedFile);

    FeedCollections deleteFeed(String id, Long userId);

    List<FeedDto> getFollowerLatestFeed(Long userId);

    List<FeedDto> getFollowerLikeFeed(Long userId);

    List<FeedDto> getPersonalFeed(Long userId);

    List<FeedDto> getLatestFeed(Pageable pageable);

    List<FeedDto> getLikeFeed(Pageable pageable);

    FeedCollections likeFeed(FeedLike feedLike);

    List<UserDetailProfile> getLikeUserList(String id);

    Long feedCount(Long userId);

    FeedSearch getSearchList(Pageable pageable,String content);

}