package com.ssafy.sunin.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFollower is a Querydsl query type for Follower
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFollower extends EntityPathBase<Follower> {

    private static final long serialVersionUID = -2008257564L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFollower follower = new QFollower("follower");

    public final DateTimePath<java.time.LocalDateTime> createDate = createDateTime("createDate", java.time.LocalDateTime.class);

    public final com.ssafy.sunin.domain.user.QUser followerMember;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.ssafy.sunin.domain.user.QUser user;

    public QFollower(String variable) {
        this(Follower.class, forVariable(variable), INITS);
    }

    public QFollower(Path<? extends Follower> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFollower(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFollower(PathMetadata metadata, PathInits inits) {
        this(Follower.class, metadata, inits);
    }

    public QFollower(Class<? extends Follower> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.followerMember = inits.isInitialized("followerMember") ? new com.ssafy.sunin.domain.user.QUser(forProperty("followerMember")) : null;
        this.user = inits.isInitialized("user") ? new com.ssafy.sunin.domain.user.QUser(forProperty("user")) : null;
    }

}

