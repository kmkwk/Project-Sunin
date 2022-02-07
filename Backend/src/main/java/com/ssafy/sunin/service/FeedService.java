package com.ssafy.sunin.service;

import com.ssafy.sunin.domain.FeedCollections;
import com.ssafy.sunin.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface FeedService {
    FeedDto updateFile(FileUpdate fileUpdate);

    FeedDto writeImageFeed(FeedVO feedVO);

    List<String> downloadFileFeed(String fileNames);

    FeedDto getDetailFeed(String id);

    FeedDto updateFeed(FeedUpdate feedUpdate);

    FeedDto addFile(FeedFile feedFile);

    void deleteFeed(String id, Long userId);

    List<FeedDto> getFollowerFeed(Long id);

    Page<FeedDto> getLatestFeed(Pageable pageable, Long userId);

    Page<FeedDto> getLikeFeed(Pageable pageable, Long userId);

    FeedDto likeFeed(FeedLike feedLike);
}