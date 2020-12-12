package com.cndsalon.domain.book;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDesigner is a Querydsl query type for Designer
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDesigner extends EntityPathBase<Designer> {

    private static final long serialVersionUID = -509464509L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDesigner designer = new QDesigner("designer");

    public final NumberPath<Integer> dAvgScore = createNumber("dAvgScore", Integer.class);

    public final StringPath dCode = createString("dCode");

    public final StringPath dDayOff = createString("dDayOff");

    public final StringPath dInfo = createString("dInfo");

    public final StringPath dName = createString("dName");

    public final NumberPath<Integer> dReviewCount = createNumber("dReviewCount", Integer.class);

    public final com.cndsalon.domain.shop.QCndSalonShopInfoVO shopInfo;

    public QDesigner(String variable) {
        this(Designer.class, forVariable(variable), INITS);
    }

    public QDesigner(Path<? extends Designer> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDesigner(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDesigner(PathMetadata metadata, PathInits inits) {
        this(Designer.class, metadata, inits);
    }

    public QDesigner(Class<? extends Designer> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.shopInfo = inits.isInitialized("shopInfo") ? new com.cndsalon.domain.shop.QCndSalonShopInfoVO(forProperty("shopInfo")) : null;
    }

}

