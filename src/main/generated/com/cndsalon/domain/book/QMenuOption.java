package com.cndsalon.domain.book;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMenuOption is a Querydsl query type for MenuOption
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QMenuOption extends BeanPath<MenuOption> {

    private static final long serialVersionUID = -486561268L;

    public static final QMenuOption menuOption = new QMenuOption("menuOption");

    public final StringPath oCode = createString("oCode");

    public final NumberPath<Integer> oPrice = createNumber("oPrice", Integer.class);

    public final NumberPath<Integer> oTime = createNumber("oTime", Integer.class);

    public QMenuOption(String variable) {
        super(MenuOption.class, forVariable(variable));
    }

    public QMenuOption(Path<? extends MenuOption> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMenuOption(PathMetadata metadata) {
        super(MenuOption.class, metadata);
    }

}

