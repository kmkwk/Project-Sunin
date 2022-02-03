package com.ssafy.sunin.service;

import com.ssafy.sunin.domain.Comment;
import com.ssafy.sunin.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    /*
     * 댓글 작성하기
     * */
    public Comment writeComment(String feedId, String writer, String content) {
        Comment comment = Comment.builder()
                .feedId(new ObjectId(feedId))
                .writer(writer)
                .content(content)
                .build();
        commentRepository.insert(comment);

        comment.setCommentGroup(comment.getId());
        return commentRepository.save(comment);
    }

    /*
     * 댓글 수정하기
     * */
    public Comment updateComment(String commentId, String content) {
        Comment comment = commentRepository.findById(new ObjectId(commentId));
        comment.setCommentModified(content);
        return commentRepository.save(comment);
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
    public Comment writeReply(String commentId, String writer, String content) {
        Comment rootComment = commentRepository.findById(new ObjectId(commentId));
        Comment comment = Comment.builder()
                .feedId(rootComment.getFeedId())
                .writer(writer)
                .content(content)
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