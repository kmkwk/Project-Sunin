package com.ssafy.sunin.repository;

import com.ssafy.sunin.domain.FeedCollections;
import java.util.List;

public interface FeedRepositoryCustom {
    List<FeedCollections> getFollowerFeed(List<String> followList);
}
