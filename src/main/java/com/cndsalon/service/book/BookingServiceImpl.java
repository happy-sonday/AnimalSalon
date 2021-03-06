package com.cndsalon.service.book;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cndsalon.domain.book.Booking;
import com.cndsalon.domain.book.BookingView;
import com.cndsalon.domain.book.Menu;
import com.cndsalon.domain.book.MenuOption;
import com.cndsalon.repository.book.BookingDao;
import com.cndsalon.repository.book.BookingDslRepository;
import com.cndsalon.repository.book.BookingRepository;
import com.cndsalon.repository.book.MenuRepository;
import com.cndsalon.util.book.TimeUtil;
import com.cndsalon.web.dto.book.DateTimeDTO;

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
	private TimeUtil timeUtil;
	
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
	public Map<String, List<DateTimeDTO>> getWorkTimeList(String sTime, String getDate, String sCode, String dCode, Long bCode) {
		// 선택한 날짜 값 핸들링
		getDate = getDate.substring(0, getDate.indexOf("("));
		LocalDate compareDate = LocalDate.parse(getDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		if(bCode == null) { bCode = (long) 0;}
		
		List<Booking> designerWorkTimeList = this.bookingRepository.findBydCodeAndsCodeAndbDate(dCode, sCode, compareDate, bCode);
		return this.timeUtil.createTimeList(sTime, compareDate, designerWorkTimeList);
	}

	@Transactional
	@Override
	public void insertBooking(Booking booking) {
		this.bookingRepository.save(booking);
		
	}

	@Transactional
	@Override
	public List<Booking> selectBookingList(String dCode, String sCode, LocalDate bDate, Long bCode) {
		return this.bookingRepository.findBydCodeAndsCodeAndbDate(dCode, sCode, bDate, bCode);
	}

	@Override
	public Boolean checkAvailableTime(int sumB, String selectedTime, List<String> xTimeList) {
		return this.timeUtil.checkAvailableTime(sumB, selectedTime, xTimeList);
	}

	@Override
	public BookingView getBookingView(String sCode, String mCode, String dCode) {
		return this.bookingDao.getBookingView(sCode, mCode, dCode);
	}


	

}
