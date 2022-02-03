package com.ssafy.sunin.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCompanyUser is a Querydsl query type for CompanyUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCompanyUser extends EntityPathBase<CompanyUser> {

    private static final long serialVersionUID = 303753675L;

    public static final QCompanyUser companyUser = new QCompanyUser("companyUser");

    public final BooleanPath approval = createBoolean("approval");

    public final StringPath companyAddress = createString("companyAddress");

    public final StringPath companyId = createString("companyId");

    public final StringPath companyName = createString("companyName");

    public final StringPath companyNickname = createString("companyNickname");

    public final StringPath companyPassword = createString("companyPassword");

    public final StringPath companyTel = createString("companyTel");

    public final DateTimePath<java.time.LocalDateTime> createdDatetime = createDateTime("createdDatetime", java.time.LocalDateTime.class);

    public final NumberPath<Long> no = createNumber("no", Long.class);

    public final EnumPath<Role> role = createEnum("role", Role.class);

    public final ListPath<String, StringPath> roles = this.<String, StringPath>createList("roles", String.class, StringPath.class, PathInits.DIRECT2);

    public QCompanyUser(String variable) {
        super(CompanyUser.class, forVariable(variable));
    }

    public QCompanyUser(Path<? extends CompanyUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCompanyUser(PathMetadata metadata) {
        super(CompanyUser.class, metadata);
    }

}

