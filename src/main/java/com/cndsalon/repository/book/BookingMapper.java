package com.cndsalon.repository.book;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cndsalon.domain.book.BookingView;
import com.cndsalon.domain.book.MenuOption;

public interface BookingMapper {

	List<MenuOption> getMenuOptionList(@Param("sCode")String sCode, @Param("mCode")String mCode);

	BookingView getBookingView(@Param("sCode")String sCode, @Param("mCode")String mCode, @Param("dCode") String dCode);
}
