package com.cndsalon.domain.book;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBookingView is a Querydsl query type for BookingView
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBookingView extends EntityPathBase<BookingView> {

    private static final long serialVersionUID = -2102302138L;

    public static final QBookingView bookingView = new QBookingView("bookingView");

    public final NumberPath<Long> bCode = createNumber("bCode", Long.class);

    public final DatePath<java.time.LocalDate> bDate = createDate("bDate", java.time.LocalDate.class);

    public final NumberPath<Integer> bPrice = createNumber("bPrice", Integer.class);

    public final StringPath bStatus = createString("bStatus");

    public final TimePath<java.time.LocalTime> bTime = createTime("bTime", java.time.LocalTime.class);

    public final StringPath dCode = createString("dCode");

    public final StringPath dName = createString("dName");

    public final StringPath id = createString("id");

    public final StringPath mCode = createString("mCode");

    public final StringPath mName = createString("mName");

    public final StringPath mpOrgName = createString("mpOrgName");

    public final StringPath mpPath = createString("mpPath");

    public final StringPath mpSysName = createString("mpSysName");

    public final StringPath sCode = createString("sCode");

    public final StringPath sName = createString("sName");

    public QBookingView(String variable) {
        super(BookingView.class, forVariable(variable));
    }

    public QBookingView(Path<? extends BookingView> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBookingView(PathMetadata metadata) {
        super(BookingView.class, metadata);
    }

}

