package com.cndsalon.web.book;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//import com.book.dto.BookingDTO;
//import com.book.service.BookingService;

@Controller
public class BookingController {
	
//	@Autowired
//	private BookingService bookingService;
	
	/**
	 * 메뉴관련
	 * */
	// 수정예정))))) 업체에서 예약하기 누를 시 이동하는 컨트롤러
	@GetMapping("/booking")
	public String moveBookingMenu() throws Exception{
		return "/booking/bookingMenu";
	}

	// bookingMenu.html 에서 강아지 선택시 화면 출력하는 컨트롤러
	@GetMapping("/choice/dog")
	public ModelAndView choiceDog() {
		ModelAndView mv = new ModelAndView("/booking/bookingMenu");

		return mv;
	}
	
	// bookingMenu.html 에서 고양이 선택시 화면 출력하는 컨트롤러
	@GetMapping("/choice/cat")
	public ModelAndView choiceCat() {
		ModelAndView mv = new ModelAndView("/booking/bookingMenu");

		return mv;
	}
	
	// bookingMenu.html 에서 예약 누를 시 bookingDetail.html 이동 컨트롤러
	@GetMapping("/detail")
	public ModelAndView moveBookingDetail() throws Exception{
		ModelAndView mv = new ModelAndView("/booking/bookingDetail");
		
		return mv;
	}
	
	/**
	 * 예약하기 관련
	 * */
	

	
	
}
