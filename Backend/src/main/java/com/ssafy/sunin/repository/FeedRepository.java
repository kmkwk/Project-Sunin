package com.ssafy.sunin.repository;

import com.ssafy.sunin.domain.FeedCollections;
import com.ssafy.sunin.dto.FeedDto;
import com.ssafy.sunin.repository.querydsl.FeedRepositoryCustom;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface FeedRepository extends MongoRepository<FeedCollections, ObjectId>,
                                        QuerydslPredicateExecutor<FeedCollections>,
        FeedRepositoryCustom {

    FeedCollections findByIdAndFlagTrue(ObjectId id);

    Page<FeedDto> findAllByUserId(Pageable pageable, String userId);
}