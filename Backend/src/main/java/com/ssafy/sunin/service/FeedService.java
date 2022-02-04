package com.ssafy.sunin.service;

import com.ssafy.sunin.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface FeedService {
    void deleteFile(String fileName);

    FeedDto writeImageFeed(FeedVO feedVO);

    List<String> downloadFileFeed(String fileNames);

    FeedDto getDetailFeed(String id);

    FeedDto updateFeed(FeedUpdate feedUpdate);

    void deleteFeed(String id);

    List<FeedDto> getFollowerFeed(Long id);

    Page<FeedDto> getLatestFeed(Pageable pageable, String userId);

    Page<FeedDto> getLikeFeed(Pageable pageable, String userId);

    FeedDto likeFeed(FeedLike feedLike);
}