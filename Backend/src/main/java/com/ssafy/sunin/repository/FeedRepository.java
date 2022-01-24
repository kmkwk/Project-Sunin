package com.ssafy.sunin.repository;

import com.ssafy.sunin.domain.FeedCollections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface FeedRepository extends MongoRepository<FeedCollections,String>,
                                        QuerydslPredicateExecutor<FeedCollections>,
                                        FeedRepositoryWrapper{
}
