package com.ssafy.sunin.service;

import com.ssafy.sunin.domain.FeedCollections;
import com.ssafy.sunin.dto.FeedDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FeedService {
    FeedDto writeFeedFile(MultipartFile files, FeedCollections feedCollections);
    FeedDto writeFeed(FeedCollections feedCollections);
    List<FeedDto> getListFeed();
    FeedDto getFeed(String id);
    FeedDto updateFeed(FeedCollections feedCollections);
    void deleteFeed(String id);
    FeedDto userFeed(String userId);
}
