package com.qcard.domains.interview.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInterview is a Querydsl query type for Interview
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInterview extends EntityPathBase<Interview> {

    private static final long serialVersionUID = -1598933284L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInterview interview = new QInterview("interview");

    public final com.qcard.domains.account.entity.QAccount account;

    public final EnumPath<com.qcard.common.enums.Category> category1 = createEnum("category1", com.qcard.common.enums.Category.class);

    public final EnumPath<com.qcard.common.enums.Category> category2 = createEnum("category2", com.qcard.common.enums.Category.class);

    public final EnumPath<com.qcard.common.enums.Category> category3 = createEnum("category3", com.qcard.common.enums.Category.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QInterview(String variable) {
        this(Interview.class, forVariable(variable), INITS);
    }

    public QInterview(Path<? extends Interview> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInterview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInterview(PathMetadata metadata, PathInits inits) {
        this(Interview.class, metadata, inits);
    }

    public QInterview(Class<? extends Interview> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.account = inits.isInitialized("account") ? new com.qcard.domains.account.entity.QAccount(forProperty("account")) : null;
    }

}

