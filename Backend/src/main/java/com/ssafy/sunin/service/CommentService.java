package com.ssafy.sunin.service;

import com.ssafy.sunin.domain.Comment;
import com.ssafy.sunin.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public List<Comment> loadComment() {
        return commentRepository.findAll();
    }

    public Comment writeComment(String writer, String content) {
        Comment comment = Comment.builder()
                .writer(writer)
                .content(content)
                .build();
        return commentRepository.insert(comment);
    }


    public Comment updateComment(String writer, String content) {
        Query query = new Query(Criteria.where("writer").is(writer));
        Update update = new Update();
        update.set("content", content);
        return null;
    }

    public Comment deleteComment(String writer) {
        Query query = new Query(Criteria.where("writer").is(writer));
        return null;
    }

}
