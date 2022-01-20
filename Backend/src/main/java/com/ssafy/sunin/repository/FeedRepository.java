package com.ssafy.sunin.repository;

import com.ssafy.sunin.domain.FeedCollections;
import com.ssafy.sunin.dto.FeedDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface FeedRepository extends MongoRepository<FeedCollections,String>{

}
