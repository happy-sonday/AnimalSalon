package com.cndsalon.repository.book;

import java.util.List;

import com.cndsalon.domain.book.BookingView;
import com.cndsalon.domain.book.MenuOption;

public interface BookingDao {
	
	public List<MenuOption> getMenuOptionList(String sCode, String mCode);

	public BookingView getBookingView(String sCode, String mCode, String dCode); 
}
