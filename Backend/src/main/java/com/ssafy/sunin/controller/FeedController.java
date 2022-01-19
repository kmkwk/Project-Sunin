package com.ssafy.sunin.controller;

import com.ssafy.sunin.domain.FeedCollections;
import com.ssafy.sunin.dto.FeedDto;
import com.ssafy.sunin.service.FeedServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feed")
@Slf4j
public class FeedController {

    private final FeedServiceImpl feedService;

//    // 피드 작성
//    @ApiOperation(value = "피드 작성")
//    @PostMapping("/")
//    public String writeFeed(
//            @RequestPart(value = "file", required = false) MultipartFile files,
//            @RequestPart(value = "feedCollections") FeedCollections feedCollections) throws FileNotFoundException {
//        log.debug("files");
//        return feedService.writeFeed(files,feedCollections);
//    }

    // 피드 작성
    @ApiOperation(value = "피드 작성")
    @PostMapping("/")
    public String writeFeed(@RequestBody FeedCollections feedCollections){
        log.debug("files");
        return feedService.writeFeed(feedCollections);
    }

    // 모든 피드 조회
    @ApiOperation(value = "모든 피드 조회")
    @GetMapping("/allList")
    public List<FeedDto> listFeed(){
        return feedService.listFeed();
    }

    // 최근 피드 조회

    // 나의 팔로워 피드 조회

    // 최신순 피드 조회

    // 좋아요순 피드 조회

    // 유저, 기업별 피드 조회


    // 피드 수정
    @PutMapping("")
    public FeedDto updateFeed(@RequestBody FeedCollections feedCollections){
        FeedDto feedDto = feedService.updateFeed(feedCollections);
        return feedDto;
    }

    // 피드 삭제
    @DeleteMapping("/{feedId}")
    public void deleteFeed(@PathVariable("feedId") String feedId ) {
        feedService.deleteFeed(feedId);
    }
}