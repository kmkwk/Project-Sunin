package com.ssafy.sunin.controller;

import com.ssafy.sunin.domain.FeedCollections;
import com.ssafy.sunin.dto.*;
import com.ssafy.sunin.predictor.FeedPredictor;
import com.ssafy.sunin.service.FeedServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feed")
@CrossOrigin("*")
@Slf4j
public class FeedController {

    private final FeedServiceImpl feedService;
//    private final FeedPredictor predictor;


    // MediaType.APPLICATION_JSON_VALUE
    // 요청을 JSON TYPE의 데이터만 담고있는 요청을 처리하겠다는 의미
    // MediaType.MULTIPART_FORM_DATA_VALUE
    //
    @ApiOperation(value = "Feed 작성", notes = "성공시 200, 다중 파일 업로드 가능")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<FeedDto> writeImageFeed(FeedVO feedVO){
        log.debug("writerImageFeed");

        if(ObjectUtils.isEmpty(feedVO)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(feedService.writeImageFeed(feedVO));
    }

    // Todo : 파일 삭제
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
        return ResponseEntity.ok(feedService.getListAllFeed());
    }

    @ApiOperation(value = "유저별 모든 피드 조회")
    @GetMapping("/userList")
    public ResponseEntity<List<FeedDto>> getUserListAllFeed(@RequestParam String userId) {
//        predictor.userId(userId);
        return ResponseEntity.ok(feedService.getUserListAllFeed(userId));
    }

    @ApiOperation(value = "기업별 모든 피드 조회")
    @GetMapping("/companyList")
    public ResponseEntity<List<FeedDto>> getCompanyListAllFeed(@RequestParam String companyId) {
//        predictor.userId(userId);
        return ResponseEntity.ok(feedService.getCompanyListAllFeed(companyId));
    }

    @ApiOperation(value = "피드 상세 페이지")
    @GetMapping("/detail")
    public ResponseEntity<FeedDto> getDetailFeed(@RequestParam String id) {
//        predictor.id(id);
        return ResponseEntity.ok(feedService.getDetailFeed(id));
    }

    // Todo : 피드 수정
    @ApiOperation(value = "피드 수정")
    @PutMapping
    public ResponseEntity<FeedDto> updateFeed(@RequestBody FeedCollections feedCollections){
        log.info("updateFeed");
        System.out.println(feedCollections);
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
//        predictor.id(id);
        feedService.deleteFeed(id);
        return ResponseEntity.ok("성공");
    }

    // Todo : 최근 피드 조회
    // created 기준으로 내림차순해서 보여줌


    // Todo : 나의 팔로워 피드 조회
    // 내 userid 기준으로 팔로워 되있는 사람들 보여줌


    // Todo : 최신순 피드 조회
    // created 기준으로 내림차순해서 보여줌
    @ApiOperation(value = "최신순 피드 조회")
    @GetMapping("/latestFeed")
    public ResponseEntity<List<FeedDto>> getLatestFeed(FeedList feedList){
        log.debug("getLatestFeed");
        return ResponseEntity.ok(feedService.getLatestFeed(feedList));
    }

    // Todo : Page 최신순 피드 조회
    // created 기준으로 내림차순해서 보여줌
    @ApiOperation(value = "최신순 피드 조회")
    @GetMapping("/pageLatest")
    public ResponseEntity<List<FeedDto>> getPageLatestFeed(@PageableDefault(size = 6, sort="createdDate",
                                                            direction = Sort.Direction.DESC) Pageable pageable){
        log.debug("getPageLatestFeed");
        return ResponseEntity.ok(feedService.getPageLatestFeed(pageable));
    }

    // Todo : 좋아요순 피드 조회
    // 좋아요 내림차순으로 보여줌
    @ApiOperation(value = "좋아요순 피드 조회")
    @GetMapping("/likeFeed")
    public ResponseEntity<List<FeedDto>> getLikesFeed(FeedList feedList){
        log.debug("getLikesFeed");
        return ResponseEntity.ok(feedService.getLikeFeed(feedList));
    }

    // Todo : 좋아요순 피드 조회
    // 좋아요 내림차순으로 보여줌
    @ApiOperation(value = "좋아요순 피드 조회")
    @GetMapping("/pageLike")
    public ResponseEntity<List<FeedDto>> getPageLikesFeed(@PageableDefault(size = 6, sort="likes",
            direction = Sort.Direction.DESC) FeedPage feedPage){
        log.debug("getPageLikesFeed");
        return ResponseEntity.ok(feedService.getPageLikeFeed(feedPage));
    }

    // Todo : 좋아요 기능
    @ApiOperation(value = "좋아요 증가")
    @PutMapping("/like")
    public ResponseEntity<FeedDto> likeFeed(FeedLike feedLike){
        log.debug("likeFeed");
        feedService.likeFeed(feedLike);

        return ResponseEntity.ok(feedService.likeFeed(feedLike));
    }

    // Todo : sun-in 일수
    @ApiOperation(value = "sun-in")
    @PutMapping("/sunin")
    public ResponseEntity<String> commitSunin(String userId){
        log.debug("commitSunin");
        return ResponseEntity.ok(feedService.commitSunin(userId));
    }
}