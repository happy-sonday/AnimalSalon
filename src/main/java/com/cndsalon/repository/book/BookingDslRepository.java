package com.cndsalon.repository.book;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cndsalon.domain.book.Booking;
import com.cndsalon.domain.book.Menu;
import com.cndsalon.domain.book.QBooking;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.cndsalon.domain.book.QMenu.*;
import static com.cndsalon.domain.shop.QCndSalonShopInfoVO.*;
import static com.cndsalon.domain.book.QBooking.*;

@Repository
public class BookingDslRepository {
	private final JPAQueryFactory queryFactory;
	
	public BookingDslRepository(JPAQueryFactory queryFactory) { // Bean으로 등록된 queryFactory를 생성자 인젝션 주입.
		this.queryFactory = queryFactory;
	}
	
	public List<Menu> findBySCode(String sCode, String mType){
		return queryFactory.selectFrom(menu)
				.join(menu.shopInfo, cndSalonShopInfoVO)
				.where(cndSalonShopInfoVO.sCode.eq(sCode), menu.mType.eq(mType))
				.fetch();
	}
	
	// getDate 비교 잘 되는지 확인!
	// 날짜(연,월,일)/ 샵코드/ 디자이너코드로 예약내역(시간) 및 소요시간 조회 
	public List<Booking> findBTime(String getDate, String sCode, String dCode){
		QBooking b = QBooking.booking;
		
		// 선택한 날짜 String -> Date 핸들링
		getDate = getDate.substring(0, getDate.indexOf("("));
		LocalDate compareDate = LocalDate.parse(getDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		return queryFactory.select(Projections.bean(Booking.class, b.bTime, b.bBeautyTime))
					.from(b)
					.where(b.bDate.eq(compareDate), b.sCode.eq(sCode), b.dCode.eq(dCode))
					.orderBy(b.dCode.asc(), b.bTime.asc())
					.fetch();

	}

}