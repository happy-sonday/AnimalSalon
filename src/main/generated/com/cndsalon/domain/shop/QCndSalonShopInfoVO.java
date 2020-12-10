package com.cndsalon.domain.shop;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCndSalonShopInfoVO is a Querydsl query type for CndSalonShopInfoVO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCndSalonShopInfoVO extends EntityPathBase<CndSalonShopInfoVO> {

    private static final long serialVersionUID = 1916467244L;

    public static final QCndSalonShopInfoVO cndSalonShopInfoVO = new QCndSalonShopInfoVO("cndSalonShopInfoVO");

    public final ListPath<com.cndsalon.domain.book.Menu, com.cndsalon.domain.book.QMenu> menus = this.<com.cndsalon.domain.book.Menu, com.cndsalon.domain.book.QMenu>createList("menus", com.cndsalon.domain.book.Menu.class, com.cndsalon.domain.book.QMenu.class, PathInits.DIRECT2);

    public final StringPath sAddr = createString("sAddr");

    public final NumberPath<Double> sAvgScore = createNumber("sAvgScore", Double.class);

    public final BooleanPath sBigdog = createBoolean("sBigdog");

    public final BooleanPath sCharge = createBoolean("sCharge");

    public final StringPath sCode = createString("sCode");

    public final StringPath sContent = createString("sContent");

    public final StringPath sGpsX = createString("sGpsX");

    public final StringPath sGpsY = createString("sGpsY");

    public final NumberPath<Double> sLocale = createNumber("sLocale", Double.class);

    public final StringPath sName = createString("sName");

    public final BooleanPath sParking = createBoolean("sParking");

    public final StringPath sPhone = createString("sPhone");

    public final StringPath sPhotoname = createString("sPhotoname");

    public final StringPath sPhotonameOrigin = createString("sPhotonameOrigin");

    public final StringPath sPhotopath = createString("sPhotopath");

    public final BooleanPath sPickup = createBoolean("sPickup");

    public final BooleanPath sSubway = createBoolean("sSubway");

    public final StringPath sTime = createString("sTime");

    public final StringPath sTitle = createString("sTitle");

    public final BooleanPath sWifi = createBoolean("sWifi");

    public final StringPath userLocalX = createString("userLocalX");

    public final StringPath userLocalY = createString("userLocalY");

    public QCndSalonShopInfoVO(String variable) {
        super(CndSalonShopInfoVO.class, forVariable(variable));
    }

    public QCndSalonShopInfoVO(Path<? extends CndSalonShopInfoVO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCndSalonShopInfoVO(PathMetadata metadata) {
        super(CndSalonShopInfoVO.class, metadata);
    }

}

