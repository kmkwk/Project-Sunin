package com.ssafy.sunin.controller;

import com.ssafy.sunin.dto.*;
import com.ssafy.sunin.service.FeedServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feed")
@CrossOrigin("*") // delete
@Slf4j
public class FeedController {

    private final FeedServiceImpl feedService;

    @ApiOperation(value = "Feed 작성", notes = "성공시 200, 다중 파일 업로드 가능")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<FeedDto> writeImageFeed(FeedVO feedVO){
        log.debug("writerImageFeed"); // log info

        if(ObjectUtils.isEmpty(feedVO)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(feedService.writeImageFeed(feedVO));
    }

    @ApiOperation(value = "file 다운로드")
    @GetMapping("/download")
    public ResponseEntity<List<String>> downloadFiles(@RequestParam(value = "fileName") String fileName){
        log.debug("downloadFiles");
        return ResponseEntity.ok(feedService.downloadFileFeed(fileName));
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

    @ApiOperation(value = "피드 상세 페이지")
    @GetMapping("/detail")
    public ResponseEntity<FeedDto> getDetailFeed(@RequestParam String id) {
        return ResponseEntity.ok(feedService.getDetailFeed(id));
    }

    @ApiOperation(value = "피드 수정")
    @PutMapping
    public ResponseEntity<FeedDto> updateFeed(@RequestBody FeedUpdate feedUpdate){
        log.info("updateFeed");
        if(ObjectUtils.isEmpty(feedUpdate)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(feedService.updateFeed(feedUpdate));

    }

    @ApiOperation(value = "피드 삭제")
    @DeleteMapping
    public ResponseEntity<String> deleteFeed(@RequestParam String id) {
        log.debug("deleteFeed methods start");
        feedService.deleteFeed(id);
        return ResponseEntity.ok("success");
    }

    @ApiOperation(value = "나의 팔로워 피드 조회", notes = "우선 나의 id 값 전달")
    @GetMapping("/followerFeed")
    public ResponseEntity<List<FeedDto>> getFollowerFeed(@RequestParam Long id){
        log.debug("getFollowerFeed");
        return ResponseEntity.ok(feedService.getFollowerFeed(id));
    }

    @ApiOperation(value = "페이징 최신순 피드 조회")
    @GetMapping("/latest")
    public ResponseEntity<Page<FeedDto>> getLatestFeed(@PageableDefault(size = 6, sort="createdDate",
                                                            direction = Sort.Direction.DESC) Pageable pageable,
                                                            @RequestParam String userId){
        log.debug("getPageLatestFeed");
        return ResponseEntity.ok(feedService.getLatestFeed(pageable,userId));
    }

    @ApiOperation(value = "페이징 좋아요순 피드 조회")
    @GetMapping("/like")
    public ResponseEntity<Page<FeedDto>> getLikesFeed(@PageableDefault(size = 6, sort="likes",
                                                            direction = Sort.Direction.DESC) Pageable pageable,
                                                           @RequestParam String userId){
        log.debug("getPageLikesFeed");
        return ResponseEntity.ok(feedService.getLikeFeed(pageable,userId));
    }

    @ApiOperation(value = "좋아요 증가 감소")
    @PutMapping("/like")
    public ResponseEntity<FeedDto> likeFeed(FeedLike feedLike){
        log.debug("likeFeed");
        return ResponseEntity.ok(feedService.likeFeed(feedLike));
    }
}