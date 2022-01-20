package com.ssafy.sunin.controller;

import com.ssafy.sunin.domain.FeedCollections;
import com.ssafy.sunin.dto.FeedDto;
import com.ssafy.sunin.service.FeedServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feed")
@CrossOrigin("*")
@Slf4j
public class FeedController {

    private final FeedServiceImpl feedService;

    // 피드 작성
    @ApiOperation(value = "피드 작성")
    @PostMapping
    public ResponseEntity<FeedDto> writeFeed(@RequestBody FeedCollections feedCollections){
        log.info("writeFeed method start");
        FeedDto feedDto = feedService.writeFeed(feedCollections);
        if(ObjectUtils.isEmpty(feedCollections)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(feedDto);
    }

    // 모든 피드 조회
    @ApiOperation(value = "모든 피드 조회")
    @GetMapping("/allList")
    public ResponseEntity<List<FeedDto>> getListFeed(){
        return new ResponseEntity<List<FeedDto>>(feedService.getListFeed(), HttpStatus.OK);
    }

    // 최근 피드 조회

    // 나의 팔로워 피드 조회

    // 최신순 피드 조회

    // 좋아요순 피드 조회

    // 유저, 기업별 피드 조회


    // 피드 수정
    @ApiOperation(value = "피드 수정")
    @PutMapping
    public ResponseEntity<FeedDto> updateFeed(@RequestBody FeedCollections feedCollections){
        log.info("updateFeed methods start");
        FeedDto feedDto = feedService.updateFeed(feedCollections);
        if(ObjectUtils.isEmpty(feedCollections)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(feedDto);

    }

    // 피드 삭제
    @ApiOperation(value = "피드 삭제")
    @DeleteMapping
    public ResponseEntity<String> deleteFeed(@RequestParam String id) {
        log.debug("deleteFeed methods start");
        return new ResponseEntity<>("success",HttpStatus.OK);
    }
}