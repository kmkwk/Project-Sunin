package com.ssafy.sunin.service;

import com.ssafy.sunin.domain.Comment;
import com.ssafy.sunin.domain.FeedCollections;
import com.ssafy.sunin.dto.comment.CommentUpdate;
import com.ssafy.sunin.dto.comment.CommentWrite;
import com.ssafy.sunin.dto.feed.FeedDto;
import com.ssafy.sunin.repository.CommentRepository;
import com.ssafy.sunin.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final FeedRepository feedRepository;
    /*
     * 댓글 작성하기
     * */
    public FeedCollections writeComment(CommentWrite commentWrite) {
        Comment comment = Comment.builder()
                .feedId(new ObjectId(commentWrite.getFeedId()))
                .writer(commentWrite.getWriter())
                .content(commentWrite.getContent())
                .build();

        FeedCollections feedCollections = feedRepository.findFeedIdById(new ObjectId(commentWrite.getFeedId()));
        commentRepository.insert(comment);
        List<Comment> comments = feedCollections.getComments();
        comments.add(comment);
        comment.setCommentGroup(comment.getId());
        feedCollections.setCommentWrite(comments);

        return feedRepository.save(feedCollections);
    }

    /*
     * 댓글 수정하기
     * */
    public Comment updateComment(CommentUpdate commentUpdate) {
        FeedCollections feedCollections = feedRepository.findFeedIdById(new ObjectId(commentUpdate.getFeedId()));
        // 피드 해당 댓글

        Comment comment = commentRepository.findById(new ObjectId(commentUpdate.getCommentId()));
        comment.setCommentModified(commentUpdate.getCommentId());
//        return commentRepository.save(comment);
        return null;
    }

    /*
     * 댓글 삭제하기
     * */
    public Comment deleteComment(String commentId) {
        Comment comment = commentRepository.findById(new ObjectId(commentId));
        comment.setCommentDeleted();
        return commentRepository.save(comment);
    }

    /*
     * 대댓글 작성하기
     * */
    public Comment writeReply(CommentWrite commentWrite) {
        Comment rootComment = commentRepository.findById(new ObjectId(commentWrite.getFeedId()));
        Comment comment = Comment.builder()
                .feedId(rootComment.getFeedId())
//                .writer(writer)
//                .content(content)
                .build();

        comment.setCommentGroup(rootComment.getGroup());
        return commentRepository.save(comment);
    }

    /*
     * 피드에 달린 댓글 리스트
     * */
    public List<Comment> findCommentsByFeed(String feedId) {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(Direction.ASC, "group"));
        orders.add(new Order(Direction.ASC, "order"));
        return commentRepository.findByFeedId(new ObjectId(feedId), Sort.by(orders));
    }

    /*
     * 피드에 달린 댓글 갯수
     * */
    public long countCommentsByFeed(String feedId) {
        return commentRepository.countByFeedId(new ObjectId(feedId));
    }

    /*
     * 개발용
     * */
    public List<String> loadAllComment() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(Direction.ASC, "group"));
        orders.add(new Order(Direction.ASC, "order"));
        List<Comment> list = commentRepository.findAll(Sort.by(orders));

        List<String> result = new ArrayList<>();
        for(Comment now : list) result.add(now.toString());
        return result;
    }

    public void resetComments() {
        commentRepository.deleteAll();
    }
}