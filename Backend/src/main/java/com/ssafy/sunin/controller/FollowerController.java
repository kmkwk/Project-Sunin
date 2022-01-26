package com.ssafy.sunin.controller;

import com.ssafy.sunin.domain.Follower;
import com.ssafy.sunin.dto.FeedDto;
import com.ssafy.sunin.dto.FollowerRequest;
import com.ssafy.sunin.service.FollowerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/follower")
@Slf4j
@RequiredArgsConstructor
public class FollowerController {

    private final FollowerService followerService;

    @ApiOperation(value = "팔로워 추가", notes = "유저 userId, 팔로워할 멤버 follower_member")
    @PostMapping("/add")
    public ResponseEntity<Long> addFollower(FollowerRequest followerRequest){
        log.debug("addFollower");
        if(ObjectUtils.isEmpty(followerRequest)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(followerService.addFollower(followerRequest));
    }

    @ApiOperation(value = "팔로워 수 조회", notes = "유저 userId, 나의 팔로워 수 조회")
    @GetMapping("/follower")
    public ResponseEntity<Long> getFollowerCount(@RequestParam Long id){
        log.debug("getFollwerCount");
        return ResponseEntity.ok(followerService.countFollower(id));
    }

    @ApiOperation(value = "팔로잉 수 조회", notes = "유저 userId, 나를 팔로워 한 수 조회")
    @GetMapping("/following")
    public ResponseEntity<Long> getFollowingCount(@RequestParam Long id){
        log.debug("getFollowingCount");
        return ResponseEntity.ok(followerService.countFollowing(id));
    }

}