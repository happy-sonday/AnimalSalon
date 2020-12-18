package com.cndsalon.service.book;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cndsalon.domain.book.Booking;
import com.cndsalon.domain.book.Menu;
import com.cndsalon.domain.book.MenuOption;
import com.cndsalon.repository.book.BookingDao;
import com.cndsalon.repository.book.BookingDslRepository;
import com.cndsalon.repository.book.BookingRepository;
import com.cndsalon.repository.book.MenuRepository;
import com.cndsalon.util.book.CreateTimeUtil;
import com.cndsalon.web.dto.book.DateTimeDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookingServiceImpl implements BookingService {
	
	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private BookingDslRepository bookingDslRepository;
	
	@Autowired
	private BookingDao bookingDao;
	
	@Autowired
	private CreateTimeUtil timeUtil;
	
	@Transactional
	@Override
	public List<Menu> getMenuList(String sCode, String mType) {
		return this.bookingDslRepository.findBySCode(sCode, mType);
	}

	@Transactional
	@Override
	public Menu getMenu(String mCode) {
		return this.menuRepository.findBymCode(mCode);
	}

	@Transactional()
	@Override
	public List<MenuOption> getMenuOptionList(String sCode, String mCode, String mType) {
		return this.bookingDao.getMenuOptionList(sCode, mCode);
	}

	@Override
	public Map<String, List<DateTimeDTO>> getWorkTimeList(String sTime, String getDate, String sCode, String dCode) {
		// 선택한 날짜 값 핸들링
		getDate = getDate.substring(0, getDate.indexOf("("));
		LocalDate compareDate = LocalDate.parse(getDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		List<Booking> designerWorkTimeList = this.bookingRepository.findBydCodeAndsCodeAndbDate(dCode, sCode, compareDate);
//		if(designerWorkTimeList != null && designerWorkTimeList.size() != 0) {
//			log.debug("BookingServiceImpl에서 테스트 : " + designerWorkTimeList.get(0).toString());
//		}
		return this.timeUtil.createTimeList(sTime, compareDate, designerWorkTimeList);
	}

	@Transactional
	@Override
	public void insertBooking(Booking booking) {
		this.bookingRepository.save(booking);
		
	}

	@Transactional
	@Override
	public List<Booking> selectBooking(String dCode, String sCode, LocalDate bDate) {
		return this.bookingRepository.findBydCodeAndsCodeAndbDate(dCode, sCode, bDate);
	}


	

}
