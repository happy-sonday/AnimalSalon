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

@Repository
public class BookingDslRepository {
	private JPAQueryFactory queryFactory;
	
	public BookingDslRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}
	
	/**
	  *
	  * <pre>
	  * 개요: 날짜(연,월,일)/ 샵코드/ 디자이너코드로 예약내역(시간) 및 소요시간 조회 
	  * </pre>
	  * @method findBTime
	  * @param getDate 선택날짜(년,월,일) [String -> LocalDate로 변환]
	  * @param sCode 업체코드
	  * @param dCode 디자이너코드
	  *
	 */
	public List<Booking> findBTime(String getDate, String sCode, String dCode){
		QBooking b = QBooking.booking;

		getDate = getDate.substring(0, getDate.indexOf("("));
		LocalDate compareDate = LocalDate.parse(getDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		return queryFactory.select(Projections.bean(Booking.class, b.bTime, b.bBeautyTime))
					.from(b)
					.where(b.bDate.eq(compareDate), b.sCode.eq(sCode), b.dCode.eq(dCode))
					.orderBy(b.dCode.asc(), b.bTime.asc())
					.fetch();

	}
	
	public List<Menu> findBySCode(String sCode, String mType){
		return queryFactory.selectFrom(menu)
				.join(menu.shopInfo, cndSalonShopInfoVO)
				.where(cndSalonShopInfoVO.sCode.eq(sCode), menu.mType.eq(mType))
				.fetch();
	}

}