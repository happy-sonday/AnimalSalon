package com.cndsalon.web.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cndsalon.domain.book.Menu;
import com.cndsalon.service.book.BookingService;
import com.cndsalon.service.book.MenuService;

import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/booking")
@Slf4j
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private MenuService menuService;
	
//	@GetMapping("/choice")
//	public @ResponseBody List<Menu> moveBookingMenu(String sCode) throws Exception{
//		sCode = "CNDSHOP_1";
//		log.info("업체코드번호 : " + sCode +" - 메뉴 조회 후 메뉴선택화면으로 이동");
//		return this.menuService.getDogMenu(sCode);
////		return "/booking/bookingMenu";
//	}
	
	// 메뉴 관련 요청사항
	/**
	  *
	  * <pre>
	  * 개요: shop_detail.html에서 예약하기 클릭 시
	  * </pre>
	  * @method moveBookingMenu
	  * @param 변수명 설명[타입]
	  * @return 메뉴 선택 기본값인 강아지메뉴 및 bookingMenu.html을 반환 [BookingController]
	  * @exception 발생예외
	  *
	 */
//	@GetMapping("/choice")
//	public @ResponseBody List<Menu> moveBookingMenu() throws Exception{
//		String sCode = "CNDSHOP_1";
//		log.info("업체코드번호 : " + sCode +" - 메뉴 조회 후 메뉴선택화면으로 이동");
//		return this.menuService.getDogMenu(sCode);
//		return "/booking/bookingMenu";
//	}
	@GetMapping("/choice")
	public @ResponseBody List<Menu> moveBookingMenu() throws Exception{
		String sCode = "CNDSHOP_1";
		log.info("업체코드번호 : " + sCode +" - 메뉴 조회 후 메뉴선택화면으로 이동");
		return this.menuService.getDogMenu(sCode);
//		return "/booking/bookingMenu";
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
