package com.cndsalon.service.book;

import java.util.List;

import com.cndsalon.domain.book.Menu;

public interface MenuService {
	List<Menu> getDogMenu(String sCode);
}
