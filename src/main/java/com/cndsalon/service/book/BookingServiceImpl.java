package com.cndsalon.service.book;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cndsalon.domain.book.Designer;
import com.cndsalon.domain.book.Menu;
import com.cndsalon.domain.book.MenuOption;
import com.cndsalon.repository.book.DesignerDslRepository;
import com.cndsalon.repository.book.MenuDslRepository;
import com.cndsalon.repository.book.MenuOptionDslRepository;
import com.cndsalon.repository.book.MenuRepository;
import com.cndsalon.util.book.TimeUtil;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {
	
	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private MenuDslRepository menuDslRepository;
	
	@Autowired
	private DesignerDslRepository designerDslRepository;
	
	@Autowired
	private MenuOptionDslRepository menuOptionRepository;
	
	@Autowired
	private TimeUtil timeUtil;
	
	@Override
	public List<Menu> getMenuList(String sCode, String mType) {
		return this.menuDslRepository.findBySCode(sCode, mType);
	}

	@Override
	public Menu getMenu(String mCode) {
		return this.menuRepository.findBymCode(mCode);
	}

	@Override
	public List<MenuOption> getMenuOptionList(String sCode, String mCode, String mType) {
		return this.menuOptionRepository.findMenuOptionByMCode(sCode, mCode, mType);
	}

	@Override
	public List<Designer> getDesignerList(String sCode) {
		return this.designerDslRepository.findBySCode(sCode);
	}

	@Override
	public String getTodaysDay() {
		return this.timeUtil.TodaysDay();
	}

	

}
