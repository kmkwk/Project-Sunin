package com.ssafy.sunin.service;

import com.ssafy.sunin.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final MongoTemplate mongoTemplate;

    public Comment findCommentById(int feedId, String commentId) {
        Query query = new Query(Criteria.where("comment_feed_id").is(feedId).and("_id").is(commentId));
        return mongoTemplate.findOne(query, Comment.class);
    }

    public Comment writeComment(int feedId, String writer, String content) {
        Comment comment = Comment.builder()
                .feedId(feedId)
                .writer(writer)
                .content(content)
                .build();
        return mongoTemplate.insert(comment);
    }

    public Comment updateComment(String comment_id, String content) {
        Query query = new Query(Criteria.where("_id").is(comment_id));
        Update update = new Update();
        update.set("comment_content", content);
        update.set("comment_updated", true);
        mongoTemplate.updateFirst(query, update, Comment.class);
        return null;
    }

    public Comment deleteComment(String comment_id) {
        Query query = new Query(Criteria.where("_id").is(comment_id));
        mongoTemplate.remove(query, Comment.class);
        return null;
    }

    public List<Comment> findCommentsByFeed(int feedId) {
        Query query = new Query(Criteria.where("comment_feed_id").is(feedId));
        return mongoTemplate.find(query, Comment.class);
    }

    public int countCommentsByFeed(int feedId) {
        Query query = new Query(Criteria.where("comment_feed_id").is(feedId));
        return mongoTemplate.find(query, Comment.class).size();
    }

    public List<Comment> loadAllComment() {
        return mongoTemplate.findAll(Comment.class);
    }

    public List<String> loadAllObjectId() {
        List<Comment> list = mongoTemplate.findAll(Comment.class);
        List<String> result = new ArrayList<>();
        for(Comment now : list) {
            result.add("ObjectID : " + now.getId()
                    + ", 내용 : " + now.getContent()
                    + ", 작성자 : " + now.getWriter()
                    + ", 수정여부 : " + now.isUpdated());
        }
        return result;
    }
}
