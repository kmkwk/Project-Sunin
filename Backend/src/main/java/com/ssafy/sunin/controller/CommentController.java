package com.ssafy.sunin.controller;

import com.ssafy.sunin.domain.Comment;
import com.ssafy.sunin.domain.FeedCollections;
import com.ssafy.sunin.dto.comment.*;
import com.ssafy.sunin.service.CommentService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestControllerAdvice(annotations = RestController.class)
@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;

    @ApiOperation(value = "댓글 작성하기", notes = "피드와 상호작용할 댓글을 입력합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "feedId", value = "피드 ObjectId", required = true),
            @ApiImplicitParam(name = "writer", value = "작성자", required = true),
            @ApiImplicitParam(name = "content", value = "내용", required = true)
    })
    @PostMapping
    public ResponseEntity<String> writeComment(@RequestBody CommentWrite commentWrite) {
        if(ObjectUtils.isEmpty(commentWrite)){
            return ResponseEntity.notFound().build();
        }
        FeedCollections result = commentService.writeComment(commentWrite);
        if(result == null) return new ResponseEntity<>("등록 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        else return new ResponseEntity<>("등록 성공", HttpStatus.CREATED);
    }

    @PutMapping
    @ApiOperation(value="댓글 수정하기", notes = "작성한 댓글을 수정합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "commentId", value = "수정할 댓글 ObjectID", required = true),
            @ApiImplicitParam(name = "content", value = "내용", required = true)
    })
    public ResponseEntity<String> updateComment(@RequestBody @Valid CommentUpdate commentUpdate) {
        if(ObjectUtils.isEmpty(commentUpdate)){
            return ResponseEntity.notFound().build();
        }
        Comment result = commentService.updateComment(commentUpdate);
        if(result == null) return new ResponseEntity<>("수정 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        else return new ResponseEntity<>("수정 성공", HttpStatus.OK);
    }

    @DeleteMapping
    @ApiOperation(value="댓글 삭제하기", notes = "작성한 댓글을 삭제합니다.")
    @ApiImplicitParam(name = "commentId", value = "삭제할 댓글 ObjectID", required = true)
    public ResponseEntity<String> deleteComment(CommentDelete commentDelete) {
        if(ObjectUtils.isEmpty(commentDelete)){
            return ResponseEntity.notFound().build();
        }
        Comment result = commentService.deleteComment(commentDelete);
        if(result == null) return new ResponseEntity<>("삭제 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        else return new ResponseEntity<>("삭제 성공", HttpStatus.OK);
    }

    @GetMapping("/all/{feedId}")
    @ApiOperation(value = "피드에 달린 댓글 전체 조회", notes = "피드ID로 해당 피드의 댓글 컬렉션을 조회합니다.")
    @ApiImplicitParam(name = "feedId", value = "피드 ObjectId", required = true)
    public ResponseEntity<Map<Object,Comment>> findCommentsByFeed(@PathVariable("feedId") String feedId) {
        return new ResponseEntity<>(commentService.findCommentsByFeed(feedId), HttpStatus.OK);
    }

    @GetMapping("/count/{feedId}")
    @ApiOperation(value = "피드에 달린 댓글 갯수 조회", notes = "피드ID로 해당 피드에 달린 댓글의 갯수를 조회합니다.")
    @ApiImplicitParam(name = "feedId", value = "피드 ObjectId", required = true)
    public ResponseEntity<Integer> countCommentsByFeed(@PathVariable("feedId") String feedId) {
        return new ResponseEntity<>(commentService.countCommentsByFeed(feedId), HttpStatus.OK);
    }

    @PostMapping("/reply")
    @ApiOperation(value = "대댓글 작성하기", notes = "대댓글을 작성합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "commentId", value = "루트댓글 ObjectId", required = true),
            @ApiImplicitParam(name = "writer", value = "작성자", required = true),
            @ApiImplicitParam(name = "content", value = "내용", required = true)
    })
    public ResponseEntity<Comment> writeReply(@RequestBody @Valid CommentReply commentReply) {
        return new ResponseEntity<>(commentService.writeReply(commentReply), HttpStatus.OK);
    }

    @PutMapping("/reply")
    @ApiOperation(value="대댓글 수정하기")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "commentId", value = "수정할 대댓글 ObjectID", required = true),
            @ApiImplicitParam(name = "content", value = "내용", required = true)
    })
    public ResponseEntity<Comment> updateReply(@RequestBody @Valid CommentUpdate commentUpdate) {
        return new ResponseEntity<>(commentService.updateComment(commentUpdate), HttpStatus.OK);
    }

    @DeleteMapping("/reply")
    @ApiOperation(value="대댓글 삭제하기")
    @ApiImplicitParam(name = "commentId", value = "삭제할 대댓글 ObjectID", required = true)
    public ResponseEntity<Comment> deleteReply(CommentDelete commentDelete) {
        return new ResponseEntity<>(commentService.deleteComment(commentDelete), HttpStatus.OK);
    }

    @ApiOperation(value = "좋아요 등록 취소")
    @PutMapping("/addLike")
    public ResponseEntity<String> likeFeed(@RequestBody @Valid CommentLike commentLike){
        log.info("likeFeed");
        if(ObjectUtils.isEmpty(commentLike)){
            return ResponseEntity.notFound().build();
        }
        commentService.likeComment(commentLike);
        return ResponseEntity.ok("success");
    }

    @ApiOperation(value = "댓글의 좋아요를 누른 유저들의 프로필, 피드 id")
    @GetMapping("/likeUser/{feedId}/{commentId}")
    public ResponseEntity<List<CommentProfile>> getLikeUserList(@PathVariable("feedId") String feedId,
                                                             @PathVariable("commentId") String commentId){
        log.info("likeUserList");
        return ResponseEntity.ok(commentService.getLikeUserList(feedId,commentId));
    }

}
