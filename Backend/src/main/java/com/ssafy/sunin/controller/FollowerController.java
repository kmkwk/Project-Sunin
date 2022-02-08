package com.ssafy.sunin.controller;

import com.ssafy.sunin.dto.follower.FollowerUser;
import com.ssafy.sunin.service.FollowerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/follower")
@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice(annotations = RestController.class)
@CrossOrigin("*")
public class FollowerController {

    private final FollowerService followerService;

    @ApiOperation(value = "팔로워 추가", notes = "userId: 현재 로그인중인 유저 id, follower_member: 팔로워할 멤버 id")
    @PostMapping
    public ResponseEntity<Long> addFollower(@RequestBody @Valid FollowerUser followerUser){
        log.info("addFollower");
        if(ObjectUtils.isEmpty(followerUser)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(followerService.addFollower(followerUser));
    }

    @ApiOperation(value = "팔로워 삭제", notes = "userId: 현재 로그인중인 유저 id, follower_member: 팔로워할 멤버 id")
    @DeleteMapping
    public ResponseEntity<String> deleteFollower(@Valid FollowerUser followerUser){
        log.info("deleteFollower");
        if(ObjectUtils.isEmpty(followerUser)){
            return ResponseEntity.notFound().build();
        }
        followerService.deleteFollower(followerUser);
        return ResponseEntity.ok("성공");
    }

    @ApiOperation(value = "팔로워 수 조회", notes = "userId: 현재 로그인중인 유저 id, 팔로워 수 조회")
    @GetMapping("/follower/{userId}")
    public ResponseEntity<Long> getFollowerCount(@PathVariable("userId") Long userId){
        log.info("getFollwerCount");
        return ResponseEntity.ok(followerService.countFollower(userId));
    }

    @ApiOperation(value = "팔로잉 수 조회", notes = "userId: 현재 로그인중인 유저 id, 팔로잉 수 조회")
    @GetMapping("/following/{userId}")
    public ResponseEntity<Long> getFollowingCount(@PathVariable("userId") Long userId){
        log.info("getFollowingCount");
        return ResponseEntity.ok(followerService.countFollowing(userId));
    }
}