package com.cndsalon.service.book;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cndsalon.domain.book.Booking;
import com.cndsalon.domain.book.Designer;
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

	@Transactional
	@Override
	public List<Designer> getDesignerList(String sCode) {
		return this.bookingDslRepository.findDesignerBySCode(sCode);
	}

	@Transactional()
	@Override
	public List<MenuOption> getMenuOptionList(String sCode, String mCode, String mType) {
		return this.bookingDao.getMenuOptionList(sCode, mCode);
	}

	@Override
	public Map<String, List<DateTimeDTO>> getWorkTimeList(String sTime, String getDate, String sCode, String dCode) {
		List<Booking> designerWorkTimeList = this.bookingDslRepository.findBTime(getDate, sCode, dCode);
		if(designerWorkTimeList != null) {
			System.out.println("BookingServiceImpl에서 테스트 : " + designerWorkTimeList.get(0).toString());
		}
		return this.timeUtil.createTimeList(sTime, getDate);
	}

	@Override
	public void insertBooking(Booking booking) {
		this.bookingRepository.save(booking);
		
	}

//	@Transactional
//	@Override
//	public void insertBooking(String bCode, String id, String mCode, String dCode, String sCode, String bDate,
//			String bTime, int bBeautyTime, int bPrice) {
//		this.bookingDslRepository.saveBooking(bCode, id, mCode, dCode, sCode, bDate, bTime, bBeautyTime, bPrice);
//	}

	

}
