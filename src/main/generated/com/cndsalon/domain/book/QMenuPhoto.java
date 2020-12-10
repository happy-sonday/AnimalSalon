package com.cndsalon.domain.book;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMenuPhoto is a Querydsl query type for MenuPhoto
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMenuPhoto extends EntityPathBase<MenuPhoto> {

    private static final long serialVersionUID = -1816130117L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMenuPhoto menuPhoto = new QMenuPhoto("menuPhoto");

    public final QMenu menu;

    public final StringPath mpOrgName = createString("mpOrgName");

    public final StringPath mpPath = createString("mpPath");

    public final StringPath mpSysName = createString("mpSysName");

    public final StringPath sCode = createString("sCode");

    public QMenuPhoto(String variable) {
        this(MenuPhoto.class, forVariable(variable), INITS);
    }

    public QMenuPhoto(Path<? extends MenuPhoto> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMenuPhoto(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMenuPhoto(PathMetadata metadata, PathInits inits) {
        this(MenuPhoto.class, metadata, inits);
    }

    public QMenuPhoto(Class<? extends MenuPhoto> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.menu = inits.isInitialized("menu") ? new QMenu(forProperty("menu"), inits.get("menu")) : null;
    }

}

