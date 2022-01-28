package com.ssafy.sunin.controller;

import com.ssafy.sunin.domain.user.User;
import com.ssafy.sunin.user.UserRequest;
import com.ssafy.sunin.user.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    @ApiOperation(value="회원가입", notes="가입성공 여부에 따라 http상태로 반환해서 알려줌")
    public ResponseEntity<String> signup(@RequestBody UserRequest request) {
        if(userService.signup(request).equals("Success")) {
            return new ResponseEntity<>("회원가입 성공", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("회원가입 실패", HttpStatus.OK);
    }

    @PostMapping("/login")
    @ApiOperation(value="로그인", notes = "이메일과 비밀번호로 로그인을 시도합니다.")
    public ResponseEntity<User> login(@RequestBody UserRequest request) {
            User loginuser = userService.login(request.getUserId(), request.getUserPassword());

            if(loginuser != null) return new ResponseEntity<>(loginuser, HttpStatus.OK);
            return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/logout")
    public Map<String, Object> logout(HttpSession session){
        session.invalidate();
        Map<String, Object> resultMap = new HashMap<>();

        resultMap.put("status", true);
        resultMap.put("msg", "로그아웃 성공");
        return resultMap;

    }

    @DeleteMapping("/delete/{userId}")
    @ApiOperation(value="회원탈퇴")
    public ResponseEntity deleteUser(@PathVariable String userId) {
        return new ResponseEntity<>(userService.deleteUser(userId), HttpStatus.OK);
    }

    @GetMapping("/searchAll")
    @ApiOperation(value="모든 회원 정보 조회")
    public ResponseEntity<List<User>> listuser() throws Exception{
        return new ResponseEntity<>(userService.listUser(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    @ApiOperation(value="회원정보를 가져온다")
    public ResponseEntity<User> detailUser(@PathVariable String userId) {
        return new ResponseEntity<>(userService.detailUser(userId), HttpStatus.OK);
    }

    @PutMapping
    @ApiOperation(value="회원정보 수정")
    public ResponseEntity updateUser(@RequestBody UserRequest request) {
        if(userService.updateUser(request).equals("Success")) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
