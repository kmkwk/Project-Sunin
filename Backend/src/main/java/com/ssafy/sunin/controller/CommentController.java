package com.ssafy.sunin.controller;

import com.ssafy.sunin.domain.Comment;
import com.ssafy.sunin.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api("댓글을 관리하는 Comment Controller")
@RequestMapping(value = "/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    @ApiOperation(value = "피드에 달린 댓글 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "feedId", value = "피드 ObjectId", required = true),
            @ApiImplicitParam(name = "commentId", value = "댓글 ObjectId", required = true)
    })
    public ResponseEntity<Comment> findCommentByFeed(int feedId, String commentId) {
        return new ResponseEntity<>(commentService.findCommentById(feedId, commentId), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "댓글 작성하기")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "feedId", value = "(#개발용#) 피드 ObjectId", required = true),
            @ApiImplicitParam(name = "writer", value = "작성자", required = true),
            @ApiImplicitParam(name = "content", value = "내용", required = true)
    })
    public ResponseEntity<Comment> writeComment(int feedId, String writer, String content) {
        return new ResponseEntity<>(commentService.writeComment(feedId, writer, content), HttpStatus.OK);
    }

    @PutMapping
    @ApiOperation(value="댓글 수정하기")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "commentId", value = "수정할 댓글 ObjectID", required = true),
            @ApiImplicitParam(name = "content", value = "내용", required = true)
    })
    public ResponseEntity<Comment> updateComment(String commentId, String content) {
        return new ResponseEntity<>(commentService.updateComment(commentId, content), HttpStatus.OK);
    }

    @DeleteMapping
    @ApiOperation(value="댓글 삭제하기")
    @ApiImplicitParam(name = "commentId", value = "삭제할 댓글 ObjectID", required = true)
    public ResponseEntity<Comment> deleteComment(String commentId) {
        return new ResponseEntity<>(commentService.deleteComment(commentId), HttpStatus.OK);
    }

    @PostMapping("/reply")
    @ApiOperation(value = "대댓글 작성하기")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "feedId", value = "(#개발용#) 피드 ObjectId", required = true),
            @ApiImplicitParam(name = "commentId", value = "(#개발용#) 루트댓글 ObjectId", required = true),
            @ApiImplicitParam(name = "writer", value = "작성자", required = true),
            @ApiImplicitParam(name = "content", value = "내용", required = true)
    })
    public ResponseEntity<Comment> writeComment(int feedId, String commentId, String writer, String content) {
        return new ResponseEntity<>(commentService.writeReply(feedId, commentId, writer, content), HttpStatus.OK);
    }

    @GetMapping("/all")
    @ApiOperation(value = "피드에 달린 댓글 전체 조회")
    @ApiImplicitParam(name = "feedId", value = "피드 ObjectId", required = true)
    public ResponseEntity<List<Comment>> findCommentsByFeed(int feedId) {
        return new ResponseEntity<>(commentService.findCommentsByFeed(feedId), HttpStatus.OK);
    }

    @GetMapping("/count")
    @ApiOperation(value = "피드에 달린 댓글 갯수 조회")
    @ApiImplicitParam(name = "feedId", value = "피드 ObjectId", required = true)
    public ResponseEntity<Integer> countCommentsByFeed(int feedId) {
        return new ResponseEntity<>(commentService.countCommentsByFeed(feedId), HttpStatus.OK);
    }

    @GetMapping("/dev/all")
    @ApiOperation(value = "#개발용# 댓글 컬렉션 전체 조회")
    public ResponseEntity<List<Comment>> loadAllComment() {
        return new ResponseEntity<>(commentService.loadAllComment(), HttpStatus.OK);
    }

    @GetMapping("/dev/string")
    @ApiOperation(value = "#개발용# 댓글 컬렉션 ObjectId 조회")
    public ResponseEntity<List<String>> loadAllObjectId() {
        return new ResponseEntity<>(commentService.loadAllObjectId(), HttpStatus.OK);
    }

}
