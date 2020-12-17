package com.cndsalon.service.book;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cndsalon.domain.book.Booking;
import com.cndsalon.repository.book.BookingDslRepository;

import lombok.extern.slf4j.Slf4j;


@SpringBootTest
@Slf4j
public class BookingTest {

	@Autowired
	BookingService bookingService;
	
	@Autowired
	BookingDslRepository bookingRepository;
	
//	@Test
//	public void 워너비_sTime(String sTime) {
//		
//		LocalTime
//		sTime = "09:00~18:00";
//		List<LocalTime> timeList =  bookingService.getWorkTimeList(sTime);
//	   log.debug("결과값: {}",timeList.get(0));
//	}
	
//	@Test
//	public void 쿼리반환값테스트() {
//		String getDate = "2020-12-16(수)";
//		String sCode = "CNDSHOP_61";
//		String dCode = "CNDDESIGNER_6";
//		
//		List<Booking> booking = bookingRepository.findBTime(getDate, sCode, dCode);
//		
//		log.debug("결과값 : " + booking.size());
//		log.debug("결과값2 : " + booking.get(0).toString());
//	}
	
//	@Test
//	public void 쿼리save테스트() {
//		String bCode = "BOOKING_30";
//		String id = "USER_1";
//		String mCode = "MENU_8";
//		String dCode = "CNDDESIGNER_6";
//		String sCode = "CNDSHOP_61";
//		String bDate = "2020-12-16";
//		String bTime = "10:00";
//		int bBeautyTime = 60;
//		int bPrice =30000;
//		
//		bookingRepository.saveBooking(bCode, id, mCode, dCode, sCode, bDate, bTime, bBeautyTime, bPrice);
//	}
}
