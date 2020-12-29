package com.cndsalon.service.book;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.cndsalon.domain.book.BookingView;

public interface BookingHomeService {
	
	List<BookingView> getBookingViewList(String id, String bStatus);

	void updateBooking(Long bCode, String bStatus, String bCancelReason);
	
	void updateBookingTime(String dCode, LocalDate bDate, LocalTime bTime, Long bCode);

	BookingView getBookingView(Long bCode);


}
