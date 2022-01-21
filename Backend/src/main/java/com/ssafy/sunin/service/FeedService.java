package com.ssafy.sunin.service;

import com.ssafy.sunin.domain.FeedCollections;
import com.ssafy.sunin.dto.FeedDto;
import com.ssafy.sunin.dto.FeedVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FeedService {
    List<FeedDto> getListFeed();
    FeedDto getFeed(String id);
    FeedDto updateFeed(FeedCollections feedCollections);
    void deleteFeed(String id);
    FeedDto userFeed(String userId);
    void deleteFile(String fileName);

    FeedDto writeImageFeed(FeedVO feedVO);
    FeedCollections getUserListFeed(String userId);

}
