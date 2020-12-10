package com.cndsalon.domain.book;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMenu is a Querydsl query type for Menu
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMenu extends EntityPathBase<Menu> {

    private static final long serialVersionUID = -1381541193L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMenu menu = new QMenu("menu");

    public final StringPath mCode = createString("mCode");

    public final ListPath<MenuOption, QMenuOption> menuOptions = this.<MenuOption, QMenuOption>createList("menuOptions", MenuOption.class, QMenuOption.class, PathInits.DIRECT2);

    public final ListPath<MenuPhoto, QMenuPhoto> menuPhotos = this.<MenuPhoto, QMenuPhoto>createList("menuPhotos", MenuPhoto.class, QMenuPhoto.class, PathInits.DIRECT2);

    public final StringPath mInfo = createString("mInfo");

    public final StringPath mName = createString("mName");

    public final NumberPath<Integer> mPrice = createNumber("mPrice", Integer.class);

    public final NumberPath<Integer> mTime = createNumber("mTime", Integer.class);

    public final StringPath mType = createString("mType");

    public final com.cndsalon.domain.shop.QCndSalonShopInfoVO shopInfo;

    public QMenu(String variable) {
        this(Menu.class, forVariable(variable), INITS);
    }

    public QMenu(Path<? extends Menu> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMenu(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMenu(PathMetadata metadata, PathInits inits) {
        this(Menu.class, metadata, inits);
    }

    public QMenu(Class<? extends Menu> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.shopInfo = inits.isInitialized("shopInfo") ? new com.cndsalon.domain.shop.QCndSalonShopInfoVO(forProperty("shopInfo")) : null;
    }

}

