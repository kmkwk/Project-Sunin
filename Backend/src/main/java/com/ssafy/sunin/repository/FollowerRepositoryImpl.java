package com.ssafy.sunin.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.sunin.domain.QFollower;
import com.ssafy.sunin.domain.user.QUser;
import com.ssafy.sunin.dto.FollowerRequest;

import javax.persistence.EntityManager;
import java.util.List;

public class FollowerRepositoryImpl implements FollowerRepositoryCustom {
    private static final QFollower qFollower = QFollower.follower;
    private static final QUser qUser = QUser.user;

    private final JPAQueryFactory queryFactory;

    public FollowerRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Long deleteFollower(FollowerRequest followerRequest) {
        return null;
    }

    @Override
    public List<String> getFollowingList(Long id) {
        /*
        select user.user_nickname from follower
        join user on follower.follower_member = user.incre
        where follower.user_id = 1;
         */
        return queryFactory
                .select(qUser.userNickname)
                .from(qFollower)
                .join(qFollower.followerMember, qUser)
                .where(qFollower.user.no.eq(id))
                .distinct()
                .fetch();
    }

    @Override
    public Long getFollowerCount(Long id) {
        // id 조회로 변경해야함 , 중복 처리 해아함
        // select * from follower wehere user_id = 1
        return queryFactory
                .select(qFollower.count())
                .from(qFollower)
                .where(qFollower.user.no.eq(id))
                .fetch().get(0);
    }

    @Override
    public Long getFollowingCount(Long followerMember) {
        // id 조회로 변경해야함,  중복 처리 해야함
        // select user_id from follower where follower_member = 1
        return queryFactory
                .select(qFollower.count())
                .from(qFollower)
                .where(qFollower.followerMember.no.eq(followerMember))
                .fetch().get(0);
    }
}
