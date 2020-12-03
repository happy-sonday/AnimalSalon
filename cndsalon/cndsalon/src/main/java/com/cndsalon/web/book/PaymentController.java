package com.cndsalon.web.book;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PaymentController {

	
	/**
	 * 결제 관련
	 * */
	// 결제 페이지로 이동하는 컨트롤러
	@GetMapping("/pay")
	public ModelAndView movePaymentPage() throws Exception{
		ModelAndView mv = new ModelAndView("/payment/paymentForm");
//		BookingDTO booking = bookingService.selectBookingInfo();
		
		// 선택한 옵션값들을 저장하여 mv에 추가해서 넘어감
//		mv.addObject("booking", booking);
		
		return mv;
	}
	
	
	// 확인용)))) 메인 페이지로 이동하는 컨트롤러
	@GetMapping("/main")
	public String moveMainPage() throws Exception{
		return "/mainPage";
	}
	
	// 결제폼 생성 컨트롤러?
}
