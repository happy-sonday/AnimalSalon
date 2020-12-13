package com.cndsalon.web.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cndsalon.service.book.BookingService;
import com.cndsalon.service.shop.ShopListService;

import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/booking")
@Slf4j
public class BookingController {
	
	@Autowired
	private ShopListService shopService;
	
	@Autowired
	private BookingService bookingService;
	
	// 메뉴 관련 요청사항
	/**
	  *
	  * <pre>
	  * 개요: shop_detail.html에서 예약하기 클릭 시
	  * </pre>
	  * @method moveBookingMenu
	  * @param 변수명 설명[타입]
	  * @return 해당 업체, 강아지 타입메뉴 조회 / bookingMenu.html 반환 [BookingController]
	  * @exception 발생예외
	  *
	 */
	@GetMapping("/shop")
	public String showMenu (
			@RequestParam("sCode")String sCode,
			Model model) {
		
		log.info("업체코드 : " + sCode + " - 업체 및 메뉴 조회 후 메뉴선택화면으로 이동");
		String mType = "강아지"; // Menu의 default 값 강아지
		
		model.addAttribute("menu", this.bookingService.getMenuList(sCode, mType));
		model.addAttribute("shop", this.shopService.getOne(sCode));
		
		return "/booking/bookingMenu";
	}
	
	/**
	  *
	  * <pre>
	  * 개요: bookingMenu.html 에서 해당메뉴 예약 클릭 시
	  * </pre>
	  * @method goBooking
	  * @param 변수명 설명[타입]
	  * @return 업체정보 및 디자이너 정보, 메뉴 및 옵션 정보 조회 및 bookingDetail.html 반환 [BookingController]
	  * @exception 발생예외
	  *
	 */
	@GetMapping("/go-booking")
	public String goBooking (
			@RequestParam("sCode")String sCode,
			@RequestParam("mCode")String mCode,
			@RequestParam("mType")String mType,
			Model model) {
		
		log.info("업체 정보 조회");
		model.addAttribute("shop", this.shopService.getOne(sCode));
		
		log.info("메뉴코드 : " + mCode + "로 메뉴조회");
		model.addAttribute("menu", this.bookingService.getMenu(mCode));
		
		log.info("메뉴옵션 : " + mType + "타입으로 옵션조회");
		model.addAttribute("menuOptions", this.bookingService.getMenuOptionList(sCode, mCode, mType));
		
		log.info("디자이너 정보 조회");
		model.addAttribute("designers", this.bookingService.getDesignerList(sCode));
		model.addAttribute("todaysDay", this.bookingService.getTodaysDay());

		return "/booking/bookingDetail";
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
		log.info("bookingDetail로 이동");
		
		return mv;
	}
	
	/**
	 * 예약하기 관련
	 * */
	

	
	
}
