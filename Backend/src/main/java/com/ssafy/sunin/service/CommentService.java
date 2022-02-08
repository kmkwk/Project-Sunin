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
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final FeedRepository feedRepository;

    /*
     * 댓글 작성하기
     * */
    public FeedCollections writeComment(CommentWrite commentWrite) {
        Comment comment = Comment.comment(commentWrite);
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
        // 댓글 전체를 빌더 해야함
        Comment comment = Comment.commentUpdate(commentUpdate);

//        feedCollections.getComments().replace(commentUpdate.getCommentId(),commentUpdate);
//        int i = feedCollections.getComments().indexOf(new ObjectId(commentUpdate.getCommentId()));
        System.out.println();
        // 이 feedcollections 에서 받은 comment에서 해당 commentId를 찾아서 update를 쳐주면됨
//        comment.setCommentModified(commentUpdate.getCommentId());
//        return commentRepository.save(comment);
        return null;
    }

    /*
     * 댓글 삭제하기
     * */
    public Comment deleteComment(String commentId) {
//        Comment comment = commentRepository.findById(new ObjectId(commentId));
//        comment.setCommentDeleted();
//        return commentRepository.save(comment);
        return null;
    }

    /*
     * 대댓글 작성하기
     * */
    public Comment writeReply(CommentWrite commentWrite) {
//        Comment rootComment = commentRepository.findById(new ObjectId(commentWrite.getFeedId()));
//        Comment comment = Comment.builder()
//                .feedId(rootComment.getFeedId())
////                .writer(writer)
////                .content(content)
//                .build();
//
//        comment.setCommentGroup(rootComment.getGroup());
//        return commentRepository.save(comment);
        return null;
    }

    /*
     * 피드에 달린 댓글 리스트
     * */
    public List<Comment> findCommentsByFeed(String feedId) {
//        List<Order> orders = new ArrayList<>();
//        orders.add(new Order(Direction.ASC, "group"));
//        orders.add(new Order(Direction.ASC, "order"));
//        return commentRepository.findByFeedId(new ObjectId(feedId), Sort.by(orders));
        return null;
    }

    /*
     * 피드에 달린 댓글 갯수
     * */
    public long countCommentsByFeed(String feedId) {

//        return commentRepository.countByFeedId(new ObjectId(feedId));
        return 2L;
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