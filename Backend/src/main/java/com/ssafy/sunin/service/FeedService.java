package com.ssafy.sunin.service;

import com.ssafy.sunin.dto.feed.*;
import com.ssafy.sunin.dto.user.UserProfile;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface FeedService {
    FeedDto updateFile(FileUpdate fileUpdate);

    FeedDto writeImageFeed(FeedWrite feedWrite);

    List<String> downloadFileFeed(String fileNames);

    FeedDto getDetailFeed(String id);

    FeedDto updateFeed(FeedUpdate feedUpdate);

    FeedDto addFile(FeedFile feedFile);

    void deleteFeed(String id, Long userId);

    List<FeedDto> getFollowerLatestFeed(Long userId);

    List<FeedDto> getFollowerLikeFeed(Long userId);

    List<FeedDto> getPersonalFeed(Long userId);

    List<FeedDto> getLatestFeed(Pageable pageable);

    List<FeedDto> getLikeFeed(Pageable pageable);

    void likeFeed(FeedLike feedLike);

    List<UserProfile> getLikeUserList(String id);
}