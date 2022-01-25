package com.ssafy.sunin.controller;

import com.ssafy.sunin.domain.user.CompanyUser;
import com.ssafy.sunin.user.CompanyUserRequest;
import com.ssafy.sunin.user.CompanyUserService;
import com.ssafy.sunin.user.JwtTokenProvider;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyUserController {

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private final JwtTokenProvider jwtTokenProvider;
    private final CompanyUserService companyUserService;



    @PostMapping("/signup")
    @ApiOperation(value="")
    public ResponseEntity signup(@RequestBody CompanyUserRequest request){
        if (companyUserService.signup(request).equals("Success")){
            return new ResponseEntity(HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login")
    @ApiOperation(value = "")
    public ResponseEntity<Map<String, Object>> login(@RequestBody CompanyUserRequest request){
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            CompanyUser loginuser = companyUserService.login(request.getCompanyId(), request.getCompanyPassword());
            if(loginuser!=null){
                String token = jwtTokenProvider.createToken(loginuser.getCompanyId(), loginuser.getRoles());
                resultMap.put("access-token", token);
                resultMap.put("message", SUCCESS);
                status = HttpStatus.ACCEPTED;
            }
            else{
                resultMap.put("message", FAIL);
                status = HttpStatus.ACCEPTED;
            }
        }
        catch (Exception e){
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @DeleteMapping("/delete/{companyId}")
    @ApiOperation(value="회원탈퇴")
    public ResponseEntity deleteCompanyUser(@PathVariable String companyId) {
        return new ResponseEntity<>(companyUserService.deleteCompanyUser(companyId), HttpStatus.OK);
    }

    @GetMapping("/searchAll")
    @ApiOperation(value="모든 회원 정보 조회")
    public ResponseEntity<List<CompanyUser>> listCompanyUser() throws Exception{
        return new ResponseEntity<>(companyUserService.listCompanyUser(), HttpStatus.OK);
    }

    @GetMapping("/{companyId}")
    @ApiOperation(value="회원정보를 가져온다")
    public ResponseEntity<CompanyUser> detailCompanyUser(@PathVariable String companyId) {
        return new ResponseEntity<>(companyUserService.detailCompanyUser(companyId), HttpStatus.OK);
    }

    @PutMapping
    @ApiOperation(value="회원정보 수정")
    public ResponseEntity updateCompanyUser(@RequestBody CompanyUserRequest request) {
        if(companyUserService.updateCompanyUser(request).equals("Success")) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

}
