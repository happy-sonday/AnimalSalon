package com.cndsalon.service.book;

import java.util.List;

import com.cndsalon.domain.book.BookingView;

public interface BookingHomeService {
	
	List<BookingView> getBookingViewList(String id, String bStatus);
}
