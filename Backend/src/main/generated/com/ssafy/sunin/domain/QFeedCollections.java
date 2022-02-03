package com.ssafy.sunin.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFeedCollections is a Querydsl query type for FeedCollections
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFeedCollections extends EntityPathBase<FeedCollections> {

    private static final long serialVersionUID = -905190767L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFeedCollections feedCollections = new QFeedCollections("feedCollections");

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public final ListPath<String, StringPath> filePath = this.<String, StringPath>createList("filePath", String.class, StringPath.class, PathInits.DIRECT2);

    public final BooleanPath flag = createBoolean("flag");

    public final ListPath<String, StringPath> hashtags = this.<String, StringPath>createList("hashtags", String.class, StringPath.class, PathInits.DIRECT2);

    public final org.bson.types.QObjectId id;

    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = createDateTime("lastModifiedDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> likes = createNumber("likes", Integer.class);

    public final MapPath<String, Object, SimplePath<Object>> likeUser = this.<String, Object, SimplePath<Object>>createMap("likeUser", String.class, Object.class, SimplePath.class);

    public final StringPath userId = createString("userId");

    public QFeedCollections(String variable) {
        this(FeedCollections.class, forVariable(variable), INITS);
    }

    public QFeedCollections(Path<? extends FeedCollections> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFeedCollections(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFeedCollections(PathMetadata metadata, PathInits inits) {
        this(FeedCollections.class, metadata, inits);
    }

    public QFeedCollections(Class<? extends FeedCollections> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.id = inits.isInitialized("id") ? new org.bson.types.QObjectId(forProperty("id")) : null;
    }

}

