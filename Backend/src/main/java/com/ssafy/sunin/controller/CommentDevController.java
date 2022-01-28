package com.ssafy.sunin.controller;

import com.ssafy.sunin.domain.Comment;
import com.ssafy.sunin.service.CommentService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/comment")
@RequiredArgsConstructor
public class CommentDevController {

    private final CommentService commentService;

    @GetMapping("/dev/all")
    @ApiOperation(value = "#개발용# 댓글 컬렉션 전체 조회")
    public ResponseEntity<List<String>> loadAllComment() {
        return new ResponseEntity<>(commentService.loadAllComment(), HttpStatus.OK);
    }

    @DeleteMapping("/dev/reset")
    @ApiOperation(value = "#개발용# 댓글 컬렉션 초기화")
    public ResponseEntity loadAllObjectId() {
        commentService.resetComments();
        return new ResponseEntity(HttpStatus.OK);
    }

}
