package com.cndsalon.domain.book;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBooking is a Querydsl query type for Booking
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBooking extends EntityPathBase<Booking> {

    private static final long serialVersionUID = 292332545L;

    public static final QBooking booking = new QBooking("booking");

    public final StringPath bCode = createString("bCode");

    public final DatePath<java.time.LocalDate> bDate = createDate("bDate", java.time.LocalDate.class);

    public final NumberPath<Integer> beautyTime = createNumber("beautyTime", Integer.class);

    public final TimePath<java.time.LocalTime> bTime = createTime("bTime", java.time.LocalTime.class);

    public final StringPath cancelReason = createString("cancelReason");

    public final StringPath dCode = createString("dCode");

    public final StringPath id = createString("id");

    public final StringPath mCode = createString("mCode");

    public final StringPath oCode = createString("oCode");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final StringPath sCode = createString("sCode");

    public final ComparablePath<Character> status = createComparable("status", Character.class);

    public QBooking(String variable) {
        super(Booking.class, forVariable(variable));
    }

    public QBooking(Path<? extends Booking> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBooking(PathMetadata metadata) {
        super(Booking.class, metadata);
    }

}

