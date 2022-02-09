package com.ssafy.sunin.repository;

import com.ssafy.sunin.domain.Comment;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface CommentRepository
        extends MongoRepository<Comment, String>,
        QuerydslPredicateExecutor<Comment> {

//    Comment findByFeedIdAndId(ObjectId feedId, ObjectId id);

    Comment findById(ObjectId id);

    List<Comment> findByFeedId(ObjectId feedId, Sort sort);

    long countByFeedId(ObjectId feedId);

}
