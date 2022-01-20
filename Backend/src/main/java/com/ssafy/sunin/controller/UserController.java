package com.ssafy.sunin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Api(tags = {"1. User"}) //UserController를 대표하는 타이틀 영역에 표시될 정보
@Slf4j
@Controller
public class UserController {

    @GetMapping("/")
    public String index(){
        return "/index.html";
    }


    @ApiOperation(value="소셜 로그인 회원 조회", notes="소셜 로그인한 회원의 정보를 조회합니다.")
    @GetMapping("/user")
    public String user(Model model, Principal principal){

        if(principal !=null){
            model.addAttribute("user",principal.getName());
        }

        log.info("사용자 이름 : "+principal.getName());
        return "/user.html";
    }

}