package com.cndsalon.domain.shop;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QShopInfoId is a Querydsl query type for ShopInfoId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QShopInfoId extends BeanPath<ShopInfoId> {

    private static final long serialVersionUID = -1618879958L;

    public static final QShopInfoId shopInfoId = new QShopInfoId("shopInfoId");

    public final StringPath sCode = createString("sCode");

    public QShopInfoId(String variable) {
        super(ShopInfoId.class, forVariable(variable));
    }

    public QShopInfoId(Path<? extends ShopInfoId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QShopInfoId(PathMetadata metadata) {
        super(ShopInfoId.class, metadata);
    }

}

