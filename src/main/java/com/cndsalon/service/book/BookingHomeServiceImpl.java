package com.cndsalon.service.book;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cndsalon.domain.book.BookingView;
import com.cndsalon.repository.book.BookingViewDslRepository;

@Service
public class BookingHomeServiceImpl implements BookingHomeService {

	@Autowired
	BookingViewDslRepository bookingViewRepository;
	
	@Transactional
	@Override
	public List<BookingView> getBookingViewList(String id, String bStatus) {
		return bookingViewRepository.findByIdAndbStatus(id, bStatus);
	}
	

}
