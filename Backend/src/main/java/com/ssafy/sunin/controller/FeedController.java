package com.ssafy.sunin.controller;

import com.ssafy.sunin.domain.FeedCollections;
import com.ssafy.sunin.dto.FeedDto;
import com.ssafy.sunin.dto.FeedVO;
import com.ssafy.sunin.predictor.FeedPredictor;
import com.ssafy.sunin.service.FeedServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feed")
@CrossOrigin("*")
@Slf4j
public class FeedController {

    private final FeedServiceImpl feedService;

    @ApiOperation(value = "Feed 작성", notes = "성공시 200, 스웨거 버전이 낮아서 작동이 안됨, 포스트맨으로")
    @PostMapping
    public ResponseEntity<FeedDto> writeImageFeed(@ModelAttribute FeedVO feedVO){
        log.debug("writerImageFeed");

        if(ObjectUtils.isEmpty(feedVO)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(feedService.writeImageFeed(feedVO));
    }

    @ApiOperation(value = "Amazon S3에 업로드 된 파일을 삭제", notes = "Amazon S3에 업로드된 파일 삭제")
    @DeleteMapping("/file")
    public ResponseEntity<String> deleteFile(@ApiParam(value="파일 하나 삭제", required = true)
                                               @RequestParam String fileName) {
        if(ObjectUtils.isEmpty(fileName)){
            return ResponseEntity.notFound().build();
        }
        feedService.deleteFile(fileName);
        return ResponseEntity.ok("성공");
    }

    @ApiOperation(value = "모든 피드 조회")
    @GetMapping("/allList")
    public ResponseEntity<List<FeedDto>> getListFeed() {
        return ResponseEntity.ok(feedService.getListFeed());
    }

    // Todo : 최근 피드 조회

    // Todo : 나의 팔로워 피드 조회

    // Todo : 최신순 피드 조회

    // Todo : 좋아요순 피드 조회

    // Todo : 유저, 기업별 피드 조회
    @ApiOperation(value = "유저별 피드 조회")
    @GetMapping("/userList")
    public ResponseEntity<FeedCollections> getUserListFeed(@RequestParam String userId) {
        System.out.println(userId);
//        FeedPredictor feedPredictor = new FeedPredictor();
//        feedPredictor.userId(userid);
        return ResponseEntity.ok(feedService.getUserListFeed(userId));
    }

    // Todo : 피드 수정
    @ApiOperation(value = "피드 수정")
    @PutMapping
    public ResponseEntity<FeedDto> updateFeed(@RequestBody @Validated FeedCollections feedCollections){
        log.info("updateFeed");

        if(ObjectUtils.isEmpty(feedCollections)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(feedService.updateFeed(feedCollections));

    }

    // Todo : 피드 삭제
    @ApiOperation(value = "피드 삭제")
    @DeleteMapping
    public ResponseEntity<String> deleteFeed(@RequestParam String id) {
        log.debug("deleteFeed methods start");
        if(ObjectUtils.isEmpty(id)){
            return ResponseEntity.notFound().build();
        }
        feedService.deleteFeed(id);
        return ResponseEntity.ok("성공");
    }
}