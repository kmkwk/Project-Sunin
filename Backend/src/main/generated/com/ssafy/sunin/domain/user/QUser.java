package com.ssafy.sunin.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -307994296L;

    public static final QUser user = new QUser("user");

    public final DateTimePath<java.time.LocalDateTime> createdDatetime = createDateTime("createdDatetime", java.time.LocalDateTime.class);

    public final ListPath<com.ssafy.sunin.domain.Follower, com.ssafy.sunin.domain.QFollower> follower = this.<com.ssafy.sunin.domain.Follower, com.ssafy.sunin.domain.QFollower>createList("follower", com.ssafy.sunin.domain.Follower.class, com.ssafy.sunin.domain.QFollower.class, PathInits.DIRECT2);

    public final NumberPath<Long> no = createNumber("no", Long.class);

    public final EnumPath<Role> role = createEnum("role", Role.class);

    public final NumberPath<Integer> suninDays = createNumber("suninDays", Integer.class);

    public final StringPath userAddress = createString("userAddress");

    public final StringPath userId = createString("userId");

    public final StringPath userName = createString("userName");

    public final StringPath userNickname = createString("userNickname");

    public final StringPath userPassword = createString("userPassword");

    public final StringPath userTel = createString("userTel");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

