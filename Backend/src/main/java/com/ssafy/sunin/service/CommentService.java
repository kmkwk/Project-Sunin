package com.ssafy.sunin.service;

import com.ssafy.sunin.domain.Comment;
import com.ssafy.sunin.domain.FeedCollections;
import com.ssafy.sunin.dto.comment.CommentDelete;
import com.ssafy.sunin.dto.comment.CommentReply;
import com.ssafy.sunin.dto.comment.CommentUpdate;
import com.ssafy.sunin.dto.comment.CommentWrite;
import com.ssafy.sunin.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final FeedRepository feedRepository;

    /*
     * 댓글 작성하기
     * */
    public FeedCollections writeComment(CommentWrite commentWrite) {
        Comment comment = Comment.commentWriter(commentWrite);
        FeedCollections feedCollections = feedRepository.findFeedIdById(new ObjectId(commentWrite.getFeedId()));
        Map<Object,Comment> comments = feedCollections.getComments();
        ObjectId objectId= new ObjectId();
        comments.put(objectId,comment);
        comment.setCommentGroup(objectId);
        feedCollections.setCommentWrite(comments);

        return feedRepository.save(feedCollections);
    }

    /*
     * 댓글 수정하기
     * */
    public Comment updateComment(CommentUpdate commentUpdate) {
        // 피드가져오기
        FeedCollections feedCollections = feedRepository.findFeedIdById(new ObjectId(commentUpdate.getFeedId()));
        // 변경하고자하는 해당 댓글
        feedCollections.getComments().get(commentUpdate.getCommentId()).setCommentModified(commentUpdate.getContent());

        feedRepository.save(feedCollections);
        return feedCollections.getComments().get(commentUpdate.getCommentId());
    }

    /*
     * 댓글 삭제하기
     * */
    public Comment deleteComment(CommentDelete commentDelete) {
        FeedCollections feedCollections = feedRepository.findFeedIdById(new ObjectId(commentDelete.getFeedId()));
        feedCollections.getComments().get(commentDelete.getCommentId()).setCommentDeleted();
        feedRepository.save(feedCollections);
        return feedCollections.getComments().get(commentDelete.getCommentId());
    }

    /*
     * 대댓글 작성하기
     * */
    public Comment writeReply(CommentReply commentReply) {
        Comment comment = Comment.commentReply(commentReply);
        FeedCollections feedCollections = feedRepository.findFeedIdById(new ObjectId(commentReply.getFeedId()));
        Map<Object,Comment> comments = feedCollections.getComments();
        comments.put(comment.getCommentId(),comment);
        feedCollections.setCommentWrite(comments);
        feedRepository.save(feedCollections);
        return comment;
    }

    /*
     * 피드에 달린 댓글 리스트
     * */
    public Map<Object,Comment> findCommentsByFeed(String feedId) {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(Direction.ASC, "group"));
        orders.add(new Order(Direction.ASC, "order"));
        return feedRepository.findFeedSortIdById(new ObjectId(feedId), Sort.by(orders)).getComments();
    }

    /*
     * 피드에 달린 댓글 갯수
     * */
    public long countCommentsByFeed(String feedId) {
//        return commentRepository.countByFeedId(new ObjectId(feedId));
        return feedRepository.findFeedIdById(new ObjectId(feedId)).getComments().size();
    }

    /*
     * 개발용
     * */
    public List<String> loadAllComment() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(Direction.ASC, "group"));
        orders.add(new Order(Direction.ASC, "order"));
//        List<Comment> list = commentRepository.findAll(Sort.by(orders));

        List<String> result = new ArrayList<>();
//        for(Comment now : list) result.add(now.toString());
//        return result;
        return null;
    }

    public void resetComments() {

//        commentRepository.deleteAll();
    }
}