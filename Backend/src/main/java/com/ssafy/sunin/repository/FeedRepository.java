package com.ssafy.sunin.repository;

import com.ssafy.sunin.domain.FeedCollections;
import com.ssafy.sunin.dto.feed.FeedDto;
import com.ssafy.sunin.repository.querydsl.FeedRepositoryCustom;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import java.util.Optional;

public interface FeedRepository extends MongoRepository<FeedCollections, ObjectId>,
                                        QuerydslPredicateExecutor<FeedCollections>,
                        FeedRepositoryCustom {

    FeedCollections findByIdAndFlagTrue(ObjectId id);

    FeedCollections findByIdAndUserIdAndFlagTrue(ObjectId id, Long userId);

    Page<FeedDto> findAllByUserId(Pageable pageable, Long userId);

    Optional<FeedCollections> findByIdAndUserId(ObjectId id, Long userId);

    FeedCollections findFeedIdById(ObjectId id);

}