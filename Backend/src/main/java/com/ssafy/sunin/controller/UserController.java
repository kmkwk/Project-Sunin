package com.ssafy.sunin.controller;

import com.ssafy.sunin.common.ApiResponse;
import com.ssafy.sunin.domain.user.User;
import com.ssafy.sunin.dto.UserLogin;
import com.ssafy.sunin.dto.UserRegister;
import com.ssafy.sunin.jwt.JwtFilter;
import com.ssafy.sunin.jwt.Token;
import com.ssafy.sunin.jwt.TokenProvider;
import com.ssafy.sunin.service.UserService;
import com.ssafy.sunin.service.UserServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
//@RequestMapping("user")
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userServiceImpl;
    private final UserService userService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @GetMapping
    public ApiResponse getUser() {

        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userServiceImpl.getUser(principal.getUsername());

        return ApiResponse.success("user", user);
    }


    @ApiOperation(value = "회원 가입을 한다.", response = String.class)
    @PostMapping()
    public ResponseEntity<String> register(@Valid @RequestBody UserRegister userRegisterDto) throws Exception {
        log.info("register user : {}", userRegisterDto);
        userService.register(userRegisterDto);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @ApiOperation(value = "로그인", response = Token.class)
    @PostMapping("/login")
    public ResponseEntity<Token> login(@Valid @ApiParam(value = "email와 password", required = true) @RequestBody UserLogin userLoginDto) throws Exception{
        log.info("call login : {}", userLoginDto);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword());
        //유저 정보를 조회하여 인증 정보를 생성
        System.out.println(authenticationToken);
        System.out.println("0번");
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        //해당 인증 정보를 현재 실행중인 스레드(Security Context)에 저장
        System.out.println("1번");
        System.out.println(authenticationManagerBuilder.getObject().authenticate(authenticationToken));
        System.out.println("2번");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //해당 인증 정보를 기반으로 jwt 토큰을 생성
        System.out.println("3번");
        String jwt = tokenProvider.createToken(authentication);
        System.out.println("4번");
        log.info("로그인 토큰정보 : {}", jwt);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        //생성된 Token을 Response Header에 넣고, Token vo 객체를 이용해 Response Body에도 넣어서 리턴
        return new ResponseEntity<>(new Token(jwt), httpHeaders, HttpStatus.OK);
    }
    @GetMapping("/test")
    public String test(){
        return "hi";
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
