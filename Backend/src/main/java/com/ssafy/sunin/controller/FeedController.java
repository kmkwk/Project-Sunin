package com.ssafy.sunin.controller;

import com.ssafy.sunin.domain.FeedCollections;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RestControllerAdvice(annotations = RestController.class)
@RequestMapping("/feed")
@Slf4j
public class FeedController {

    private final FeedServiceImpl feedService;

    @ApiOperation(value = "Feed 작성", notes = "성공시 200, 다중 파일 업로드 가능")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<FeedDto> writeImageFeed(@RequestBody @Valid FeedVO feedVO){
        log.info("writerImageFeed"); // log info

        if(ObjectUtils.isEmpty(feedVO)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(feedService.writeImageFeed(feedVO));
    }

    @ApiOperation(value = "file 다운로드")
    @GetMapping("/download")
    public ResponseEntity<List<String>> downloadFiles(@RequestParam("fileName") String fileName){
        log.info("downloadFiles");
        return ResponseEntity.ok(feedService.downloadFileFeed(fileName));
    }

    // Todo : 프론트에서 encodeURI로 감싸서 보내는거 테스트해봐야함
    @ApiOperation(value = "피드 사진 삭제", notes = "사진 여러장 삭제 가능, encodeURI로 감싸서 보내야함")
    @PutMapping("/file")
    public ResponseEntity<FeedDto> updateFile(@RequestBody @Valid FeedFile feedFile) {
        log.info("deleteFile");
        if(ObjectUtils.isEmpty(feedFile)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(feedService.updateFile(feedFile));
    }

    @ApiOperation(value = "피드 사진 추가", notes = "사진 여러장 추가 가능, encodeURI로 감싸서 보내")
    @PutMapping("/addFile")
    public ResponseEntity<FeedDto> addFile(@RequestBody @Valid FeedFile feedFile) {
        log.info("addFile");
        if(ObjectUtils.isEmpty(feedFile)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(feedService.addFile(feedFile));
    }

    @ApiOperation(value = "피드 상세 페이지")
    @GetMapping("/detail/{id}")
    public ResponseEntity<FeedDto> getDetailFeed(@PathVariable("id") String id) {
        log.info("getDetailFeed");
        return ResponseEntity.ok(feedService.getDetailFeed(id));
    }

    @ApiOperation(value = "피드 수정")
    @PutMapping
    public ResponseEntity<FeedDto> updateFeed(@RequestBody @Valid FeedUpdate feedUpdate){
        log.info("updateFeed");
        if(ObjectUtils.isEmpty(feedUpdate)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(feedService.updateFeed(feedUpdate));

    }

    @ApiOperation(value = "피드 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFeed(@PathVariable("id") String id) {
        log.info("deleteFeed");
        feedService.deleteFeed(id);
        return ResponseEntity.ok("success");
    }

    @ApiOperation(value = "나의 팔로워 피드 조회", notes = "우선 나의 id 값 전달")
    @GetMapping("/follower/{id}")
    public ResponseEntity<List<FeedDto>> getFollowerFeed(@PathVariable("id") Long id){
        log.info("getFollowerFeed");
        return ResponseEntity.ok(feedService.getFollowerFeed(id));
    }

    @ApiOperation(value = "페이징 최신순 피드 조회")
    @GetMapping("/latest")
    public ResponseEntity<Page<FeedDto>> getLatestFeed(@PageableDefault(size = 6, sort="createdDate",
                                                            direction = Sort.Direction.DESC) Pageable pageable,
                                                            @RequestParam("userId") String userId){
        log.info("getLatestFeed");
        return ResponseEntity.ok(feedService.getLatestFeed(pageable,userId));
    }

    @ApiOperation(value = "페이징 좋아요순 피드 조회")
    @GetMapping("/like")
    public ResponseEntity<Page<FeedDto>> getLikesFeed(@PageableDefault(size = 6, sort="likes",
                                                            direction = Sort.Direction.DESC) Pageable pageable,
                                                           @RequestParam("userId") String userId){
        log.info("getLikesFeed");
        return ResponseEntity.ok(feedService.getLikeFeed(pageable,userId));
    }

    @ApiOperation(value = "좋아요 등록 취소")
    @PutMapping("/likes")
    public ResponseEntity<FeedDto> likeFeed(@RequestBody @Valid FeedLike feedLike){
        log.info("likeFeed");
        return ResponseEntity.ok(feedService.likeFeed(feedLike));
    }
}