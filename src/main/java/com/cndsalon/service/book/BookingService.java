package com.cndsalon.service.book;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.cndsalon.domain.book.Booking;
import com.cndsalon.domain.book.BookingView;
import com.cndsalon.domain.book.Menu;
import com.cndsalon.domain.book.MenuOption;
import com.cndsalon.web.dto.book.DateTimeDTO;

public interface BookingService {
	List<Menu> getMenuList(String sCode, String mType);

	Menu getMenu(String mCode);
	
	List<MenuOption> getMenuOptionList(String sCode, String mCode, String mType);
	
	Map<String, List<DateTimeDTO>> getWorkTimeList(String sTime, String getDate, String sCode, String dCode);
	
	void insertBooking(Booking booking);

	List<Booking> selectBookingList(String dCode, String sCode, LocalDate bDate);
	
	Boolean checkAvailableTime(int sumB, String selectedTime, List<String> xTimeList);
	
	BookingView getBookingView(String sCode, String mCode, String dCode);
	
	 
}
