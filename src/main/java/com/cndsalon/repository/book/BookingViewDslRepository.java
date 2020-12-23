package com.cndsalon.repository.book;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cndsalon.domain.book.BookingView;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.cndsalon.domain.book.QBookingView.*;

@Repository
public class BookingViewDslRepository {
	private JPAQueryFactory queryFactory;
	
	public BookingViewDslRepository(JPAQueryFactory queryFactory) { // Bean으로 등록된 queryFactory를 생성자 인젝션 주입.
		this.queryFactory = queryFactory;
	}
	
	public List<BookingView> findByIdAndbStatus(String id, String bStatus){
		
		BooleanBuilder builder = new BooleanBuilder();
		
		if(bStatus.equals("all")) {
			builder.and(bookingView.bStatus.notIn("3"));
		} else {
			builder.and(bookingView.bStatus.eq(bStatus));
		}
		
		List<BookingView> bView = queryFactory.selectFrom(bookingView)
				.where(bookingView.id.eq(id))
				.orderBy(bookingView.bDate.desc(), bookingView.bTime.asc())
				.fetch();
		return bView;
	}
	
}
