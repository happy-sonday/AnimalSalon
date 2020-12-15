package com.cndsalon.service.book;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cndsalon.domain.book.Designer;
import com.cndsalon.domain.book.Menu;
import com.cndsalon.domain.book.MenuOption;
import com.cndsalon.repository.book.BookingDao;
import com.cndsalon.repository.book.DesignerDslRepository;
import com.cndsalon.repository.book.MenuDslRepository;
import com.cndsalon.repository.book.MenuRepository;
import com.cndsalon.util.book.TimeUtil;
import com.cndsalon.web.dto.book.DateTimeDTO;

@Service
public class BookingServiceImpl implements BookingService {
	
	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private MenuDslRepository menuDslRepository;
	
	@Autowired
	private DesignerDslRepository designerDslRepository;
	
	@Autowired
	private BookingDao bookingDao;
	
	@Autowired
	private TimeUtil timeUtil;
	
	@Transactional
	@Override
	public List<Menu> getMenuList(String sCode, String mType) {
		return this.menuDslRepository.findBySCode(sCode, mType);
	}

	@Transactional
	@Override
	public Menu getMenu(String mCode) {
		return this.menuRepository.findBymCode(mCode);
	}

	@Transactional
	@Override
	public List<Designer> getDesignerList(String sCode) {
		return this.designerDslRepository.findDesignerBySCode(sCode);
	}

	@Transactional
	@Override
	public List<MenuOption> getMenuOptionList(String sCode, String mCode, String mType) {
		return this.bookingDao.getMenuOptionList(sCode, mCode);
	}

	@Override
	public Map<String, List<DateTimeDTO>> getWorkTimeList(String sTime, String getDate) {
		return this.timeUtil.createTimeList(sTime, getDate);
	}

	

}
