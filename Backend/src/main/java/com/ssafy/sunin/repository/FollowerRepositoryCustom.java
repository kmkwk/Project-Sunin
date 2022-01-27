package com.ssafy.sunin.repository;

import java.util.List;

public interface FollowerRepositoryCustom {

    // Todo : 팔로워 추가
    // Todo : 팔로워 삭제
    // Todo : 팔로잉 조회 - 팔로잉 리스트 가져와서 팔로잉 유저 최신순 피드 가져오기
    List<String> getFollowingList(Long id);
    // Todo : 팔로워 카운트
    Long getFollowerCount(Long id);
    // Todo : 팔로잉 카운트
    Long getFollowingCount(Long followerMember);
}
