package com.cndsalon.web.book;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cndsalon.web.shop.CndSalonShopController;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
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
	
	// 결제성공 페이지로 이동하는 컨트롤러
	@RequestMapping(value="/paySuccess", method=RequestMethod.GET)
	public String moveSuccessPage() throws Exception{
		return "/payment/afterPaySuccess";
	}
	
	// 결제성공 시 DB에 데이터 저장
	@RequestMapping(value="/paySuccess", method = RequestMethod.POST)
	public  String insertPaymentInfo(@RequestParam("merchant_uid") String pCode, @RequestParam("pg") String pg,
			@RequestParam("pay_method") String pMethod, @RequestParam("amount") Integer pPrice,
			@RequestParam("buyer_email") String buyerEmail, @RequestParam("buyer_tel") String buyerTel) {
		log.info("===Insert PaymentInfo start===");
		
		return null;
	}
	
	
	// 결제실패페이지로 이동하는 컨트롤러
	@RequestMapping(value="/payFail", method=RequestMethod.GET)
	public String moveFailPage() throws Exception{
	return "/payment/afterPayFailed";
	}
}
