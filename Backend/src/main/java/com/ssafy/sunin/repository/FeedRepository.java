package com.ssafy.sunin.repository;

import com.ssafy.sunin.domain.FeedCollections;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeedRepository extends MongoRepository<FeedCollections,String> {

    // 최근 피드 조회

    // 나의 팔로워 피드 조회

    // 최신순 피드 조회

    // 좋아요순 피드 조회

    // 유저, 기업별 피드 조회

}
