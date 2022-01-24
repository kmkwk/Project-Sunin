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

    public Comment findCommentById(int feedId, String commentId) {
        return commentRepository.findByFeedIdAndId(feedId, new ObjectId(commentId));
    }

    public Comment writeComment(int feedId, String writer, String content) {
        Comment comment = Comment.builder()
                .feedId(feedId)
                .writer(writer)
                .content(content)
                .build();
        commentRepository.insert(comment);

        comment.setGroup(comment.getId());
        commentRepository.save(comment);
        return null;
    }

    public Comment updateComment(String commentId, String content) {
        Comment comment = commentRepository.findById(new ObjectId(commentId));
        comment.setUpdated(true);
        comment.setContent(content);
        commentRepository.save(comment);
        return null;
    }

    public Comment deleteComment(String commentId) {
        Comment comment = commentRepository.findById(new ObjectId(commentId));
        comment.setDeleted(true);
        commentRepository.save(comment);
        return null;
    }

    public Comment writeReply(int feedId, String commentId, String writer, String content) {
        Comment rootComment = commentRepository.findByFeedIdAndId(feedId, new ObjectId(commentId));
        Comment comment = Comment.builder()
                .feedId(feedId)
                .writer(writer)
                .content(content)
                .build();

        comment.setGroup(rootComment.getGroup());
        comment.setOrder(comment.getOrder()+1);

        commentRepository.save(comment);
        return null;
    }

    public List<Comment> findCommentsByFeed(int feedId) {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(Direction.ASC, "group"));
        orders.add(new Order(Direction.ASC, "order"));
        List<Comment> list = commentRepository.findByFeedId(feedId, Sort.by(orders));

        return list;
    }

    public long countCommentsByFeed(int feedId) {
        return commentRepository.countByFeedId(feedId);
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
