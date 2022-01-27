package com.ssafy.sunin.service;

import com.ssafy.sunin.dto.*;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface FeedService {
    void deleteFile(String fileName);

    FeedDto writeImageFeed(FeedVO feedVO);

    FeedDto getDetailFeed(String id);

    FeedDto updateFeed(FeedUpdate feedUpdate);

    void deleteFeed(String id);

    List<FeedDto> getFollowerFeed(Long id);

    List<FeedDto> getLatestFeed(FeedList feedList);

    List<FeedDto> getPageLatestFeed(Pageable pageable);

    List<FeedDto> getLikeFeed(FeedList feedList);

    List<FeedDto> getPageLikeFeed(FeedPage feedPage);

    FeedDto likeFeed(FeedLike feedLike);

    String commitSunin(String userId);
}