package com.ssafy.sunin.controller;

import com.ssafy.sunin.common.ApiResponse;
import com.ssafy.sunin.domain.user.User;
import com.ssafy.sunin.payload.request.user.UserUpdateRequest;
import com.ssafy.sunin.payload.response.user.*;
import com.ssafy.sunin.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation(value="user 반환")
    @GetMapping
    public ApiResponse getUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUser(principal.getUsername());
        UserDto userDto = UserDto.userDto(user);

        return ApiResponse.success("user", userDto);
    }

    @ApiOperation(value = "유저 디테일 프로필 조회", notes = "사진, 닉네임, 소개, 팔로워 수, 팔로잉 수, 개인 피드 수, 썬인 수")
    @GetMapping("/profile/{userId}")
    public ResponseEntity<UserDetailProfile> getUserDetailProfile(@PathVariable("userId") Long userId){
        log.info("getUserProfile");
        if(ObjectUtils.isEmpty(userId)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userService.getUserDetailProfile(userId));
    }

    @ApiOperation(value = "유저 사이드바 프로필 조회", notes = "사이드바 사진, 닉네임, 썬인 수")
    @GetMapping("/profileSide/{userId}")
    public ResponseEntity<UserSideProfile> getUserProfile(@PathVariable("userId") Long userId){
        log.info("getUserProfile");
        if(ObjectUtils.isEmpty(userId)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userService.getUserSideProfile(userId));
    }

    @ApiOperation(value = "유저 썬인 랭크")
    @GetMapping("/rank")
    public ResponseEntity<List<UserRank>> getUserLank(){
        log.info("getUserRank");

        return ResponseEntity.ok(userService.getUserListLank());
    }

    @ApiOperation(value = "유저 프로필 수정")
    @PutMapping
    public ResponseEntity<UserUpdateResponse> updateUser(@RequestPart @Valid UserUpdateRequest userUpdateRequest,
                                                         @RequestPart MultipartFile image) {
        log.info("updateUser");

        return ResponseEntity.ok(userService.updateUser(userUpdateRequest,image));
    }

    @ApiOperation(value = "유저 프로필 사진만 수정")
    @PutMapping("/image")
    public ResponseEntity<UserDetailProfile> updateUserImage(@RequestPart @Valid Long id,
                                                             @RequestPart MultipartFile image){
        log.info("updateUserImage");

        return ResponseEntity.ok(userService.updateUserImage(id,image));
    }

    @ApiOperation(value = "유저 프로필 사진만 삭제, 구현 안됨 해야되면 말해주세요")
    @DeleteMapping("image/{userId}")
    public ResponseEntity<UserDetailProfile> deleteUserImage(@PathVariable("userId") Long userId){
        log.info("deleteUserImage");
        if(ObjectUtils.isEmpty(userId)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userService.delteUserImage(userId));
    }
}