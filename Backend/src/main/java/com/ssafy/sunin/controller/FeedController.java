package com.ssafy.sunin.controller;

import com.ssafy.sunin.domain.Comment;
import com.ssafy.sunin.service.CommentService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/feed")
@RequiredArgsConstructor
public class FeedController {

    private final CommentService commentService;

    @GetMapping
    @ApiOperation(value="피드 전체 조회")
    public ResponseEntity<List<Comment>> findAllFeed() {
        return new ResponseEntity<>(commentService.loadComment(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Comment> writeComment(String writer, String content) {
        return new ResponseEntity<>(commentService.writeComment(writer, content), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Comment> updateComment(String writer, String content) {
        return new ResponseEntity<>(commentService.updateComment(writer, content), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Comment> deleteComment(String writer) {
        return new ResponseEntity<>(commentService.deleteComment(writer), HttpStatus.OK);
    }

}
