package com.ssafy.sunin.controller;

import com.ssafy.sunin.dto.FollowerVO;
import com.ssafy.sunin.service.FollowerService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/follower")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin("*")
public class FollowerController {

    private final FollowerService followerService;

    @ApiOperation(value = "팔로워 추가", notes = "userId: 현재 로그인중인 유저 id, follower_member: 팔로워할 멤버 id")
    @PostMapping
    public ResponseEntity<Long> addFollower(FollowerVO followerVO){
        log.debug("addFollower");
        if(ObjectUtils.isEmpty(followerVO)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(followerService.addFollower(followerVO));
    }

    @ApiOperation(value = "팔로워 삭제", notes = "userId: 현재 로그인중인 유저 id, follower_member: 팔로워할 멤버 id")
    @DeleteMapping
    public ResponseEntity<String> deleteFollower(FollowerVO followerVO){
        log.debug("deleteFollower");
        if(ObjectUtils.isEmpty(followerVO)){
            return ResponseEntity.notFound().build();
        }
        followerService.deleteFollower(followerVO);
        return ResponseEntity.ok("성공");
    }

    @ApiOperation(value = "팔로워 수 조회", notes = "userId: 현재 로그인중인 유저 id, 팔로워 수 조회")
    @GetMapping("/follower")
    public ResponseEntity<Long> getFollowerCount(@RequestParam Long id){
        log.debug("getFollwerCount");
        return ResponseEntity.ok(followerService.countFollower(id));
    }

    @ApiOperation(value = "팔로잉 수 조회", notes = "userId: 현재 로그인중인 유저 id, 팔로잉 수 조회")
    @GetMapping("/following")
    public ResponseEntity<Long> getFollowingCount(@RequestParam Long id){
        log.debug("getFollowingCount");
        return ResponseEntity.ok(followerService.countFollowing(id));
    }
}