package com.ssafy.sunin.controller;

import com.ssafy.sunin.common.ApiResponse;
import com.ssafy.sunin.domain.user.User;
import com.ssafy.sunin.payload.request.feed.ImageUpdate;
import com.ssafy.sunin.payload.request.user.UserUpdateRequest;
import com.ssafy.sunin.payload.response.user.UserDetailProfile;
import com.ssafy.sunin.payload.response.user.UserRank;
import com.ssafy.sunin.payload.response.user.UserSideProfile;
import com.ssafy.sunin.payload.response.user.UserUpdateResponse;
import com.ssafy.sunin.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
        System.out.println();
        return ApiResponse.success("user", user);
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
    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<UserUpdateResponse> updateUser(@RequestBody @Valid UserUpdateRequest userUpdateRequest) {
        log.info("updateUser");

        return ResponseEntity.ok(userService.updateUser(userUpdateRequest));
    }

    @ApiOperation(value = "유저 프로필 사진만 수정")
    @PutMapping("/image")
    public ResponseEntity<UserDetailProfile> updateUserImage(@RequestBody @Valid ImageUpdate imageUpdate){
        log.info("updateUserImage");
        if(ObjectUtils.isEmpty(imageUpdate)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userService.updateUserImage(imageUpdate));
    }

    @ApiOperation(value = "유저 프로필 사진만 삭제")
    @DeleteMapping("image/{userId}")
    public ResponseEntity<UserDetailProfile> deleteUserImage(@PathVariable("userId") Long userId){
        log.info("deleteUserImage");
        if(ObjectUtils.isEmpty(userId)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userService.delteUserImage(userId));
    }

//    @PostMapping("/signup")
//    @ApiOperation(value="회원가입", notes="가입성공 여부에 따라 http상태로 반환해서 알려줌")
//    public ResponseEntity<String> signup(@RequestBody UserRequest request) {
//        if(userService.signup(request).equals("Success")) {
//            return new ResponseEntity<>("회원가입 성공", HttpStatus.CREATED);
//        }
//        return new ResponseEntity<>("회원가입 실패", HttpStatus.OK);
//    }
//
//    @PostMapping("/login")
//    @ApiOperation(value="로그인", notes = "이메일과 비밀번호로 로그인을 시도합니다.")
//    public ResponseEntity<User> login(@RequestBody UserRequest request) {
//            User loginuser = userService.login(request.getUserId(), request.getUser_password());
//
//            if(loginuser != null) return new ResponseEntity<>(loginuser, HttpStatus.OK);
//            return new ResponseEntity<>(null, HttpStatus.OK);
//    }
//
//    @GetMapping("/logout")
//    public Map<String, Object> logout(HttpSession session){
//        session.invalidate();
//        Map<String, Object> resultMap = new HashMap<>();
//
//        resultMap.put("status", true);
//        resultMap.put("msg", "로그아웃 성공");
//        return resultMap;
//
//    }
//
//    @DeleteMapping("/delete/{userId}")
//    @ApiOperation(value="회원탈퇴")
//    public ResponseEntity deleteUser(@PathVariable String userId) {
//        return new ResponseEntity<>(userService.deleteUser(userId), HttpStatus.OK);
//    }
//
//    @GetMapping("/searchAll")
//    @ApiOperation(value="모든 회원 정보 조회")
//    public ResponseEntity<List<User>> listuser() throws Exception{
//        return new ResponseEntity<>(userService.listUser(), HttpStatus.OK);
//    }
//
//    @GetMapping("/{userId}")
//    @ApiOperation(value="회원정보를 가져온다")
//    public ResponseEntity<User> detailUser(@PathVariable String userId) {
//        return new ResponseEntity<>(userService.detailUser(userId), HttpStatus.OK);
//    }
//
//    @PutMapping
//    @ApiOperation(value="회원정보 수정")
//    public ResponseEntity updateUser(@RequestBody UserRequest request) {
//        if(userService.updateUser(request).equals("Success")) {
//            return new ResponseEntity(HttpStatus.OK);
//        }
//        return new ResponseEntity(HttpStatus.BAD_REQUEST);
//    }
}
