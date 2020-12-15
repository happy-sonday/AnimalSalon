package com.cndsalon.service.book;

import java.util.List;
import java.util.Map;

import com.cndsalon.domain.book.Designer;
import com.cndsalon.domain.book.Menu;
import com.cndsalon.domain.book.MenuOption;
import com.cndsalon.web.dto.book.DateTimeDTO;

public interface BookingService {
	List<Menu> getMenuList(String sCode, String mType);

	Menu getMenu(String mCode);
	
	List<MenuOption> getMenuOptionList(String sCode, String mCode, String mType);
	
	List<Designer> getDesignerList(String sCode);
	
	Map<String, List<DateTimeDTO>> getWorkTimeList(String sTime, String getDate);
}
