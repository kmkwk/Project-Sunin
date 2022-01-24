package com.ssafy.sunin.service;

import com.ssafy.sunin.domain.FeedCollections;
import com.ssafy.sunin.dto.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FeedService {
    List<FeedDto> getListAllFeed();
    void deleteFile(String fileName);

    FeedDto writeImageFeed(FeedVO feedVO);
    // Todo : 상세 페이지
    FeedDto getDetailFeed(String id);
    // Todo : 개인 글 - 최신 순
    List<FeedDto> getUserListAllFeed(String userId);
    // Todo : 기업 글 - 최신 순, 좋아요 순, select 박스 연동되는 식으로
    List<FeedDto> getCompanyListAllFeed(String companyId);
    FeedDto updateFeed(FeedCollections feedCollections);
    void deleteFeed(String id);

    // Todo : 나의 팔로워 피드 조회
    // 내 userid 기준으로 팔로워 되있는 사람들 보여줌
    List<FeedDto> getFollwerFeed(String userId);

    // Todo : 최신 피드 조회
    // created 기준으로 내림차순해서 보여줌
    List<FeedDto> getLatestFeed(FeedList feedList);
    List<FeedDto> getPageLatestFeed(Pageable pageable);

    // Todo : 좋아요순 피드 조회
    // 좋아요 내림차순으로 보여줌
    List<FeedDto> getLikeFeed(FeedList feedList);
    List<FeedDto> getPageLikeFeed(FeedPage feedPage);

    // Todo : 좋아요 기능
    FeedDto likeFeed(FeedLike feedLike);

    // Todo : sun-in 일수
    String commitSunin(String userId);

}
