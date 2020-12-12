package com.cndsalon.domain.book;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMenuOption is a Querydsl query type for MenuOption
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMenuOption extends EntityPathBase<MenuOption> {

    private static final long serialVersionUID = -486561268L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMenuOption menuOption = new QMenuOption("menuOption");

    public final StringPath mCode = createString("mCode");

    public final QMenu menu;

    public final StringPath oName = createString("oName");

    public final NumberPath<Integer> oPrice = createNumber("oPrice", Integer.class);

    public final NumberPath<Integer> oTime = createNumber("oTime", Integer.class);

    public final StringPath sCode = createString("sCode");

    public final com.cndsalon.domain.shop.QCndSalonShopInfoVO shopInfo;

    public QMenuOption(String variable) {
        this(MenuOption.class, forVariable(variable), INITS);
    }

    public QMenuOption(Path<? extends MenuOption> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMenuOption(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMenuOption(PathMetadata metadata, PathInits inits) {
        this(MenuOption.class, metadata, inits);
    }

    public QMenuOption(Class<? extends MenuOption> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.menu = inits.isInitialized("menu") ? new QMenu(forProperty("menu"), inits.get("menu")) : null;
        this.shopInfo = inits.isInitialized("shopInfo") ? new com.cndsalon.domain.shop.QCndSalonShopInfoVO(forProperty("shopInfo")) : null;
    }

}

