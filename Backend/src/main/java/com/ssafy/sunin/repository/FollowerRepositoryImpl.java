package com.ssafy.sunin.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.sunin.domain.QFollower;
import com.ssafy.sunin.domain.user.QUser;
import com.ssafy.sunin.domain.user.User;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

public class FollowerRepositoryImpl implements FollowerRepositoryCustom {
    private static final QFollower qFollower = QFollower.follower;
    private static final QUser qUser = QUser.user;

    private final JPAQueryFactory queryFactory;

    public FollowerRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Transactional
    @Override
    public Long deleteFollower(User user, User followerMember) {
        return queryFactory
                .delete(qFollower)
                .where(qFollower.followerMember.eq(followerMember).and(qFollower.user.eq(user)))
                .execute();
    }

    @Override
    public List<Long> getUser(User user, User followerMember) {
        return queryFactory
                .select(qFollower.id)
                .from(qFollower)
                .where(qFollower.followerMember.eq(followerMember).and(qFollower.user.eq(user)))
                .fetch();
    }

    @Override
    public List<String> getFollowingList(Long id) {
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
        return queryFactory
                .select(qFollower.count())
                .from(qFollower)
                .where(qFollower.user.no.eq(id))
                .fetch().get(0);
    }

    @Override
    public Long getFollowingCount(Long followerMember) {
        return queryFactory
                .select(qFollower.count())
                .from(qFollower)
                .where(qFollower.followerMember.no.eq(followerMember))
                .fetch().get(0);
    }
}
