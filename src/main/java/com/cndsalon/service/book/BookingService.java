package com.cndsalon.service.book;

import java.util.List;

import com.cndsalon.domain.book.Designer;
import com.cndsalon.domain.book.Menu;
import com.cndsalon.domain.book.MenuOption;

public interface BookingService {
	List<Menu> getMenuList(String sCode, String mType);

	Menu getMenu(String mCode);
	
	List<MenuOption> getMenuOptionList(String sCode, String mCode, String mType);
	
	List<Designer> getDesignerList(String sCode);
	
	String getTodaysDay();
}
