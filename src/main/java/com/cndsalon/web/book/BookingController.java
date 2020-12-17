package com.cndsalon.web.book;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cndsalon.domain.book.Booking;
import com.cndsalon.repository.book.BookingRepository;
import com.cndsalon.service.book.BookingService;
import com.cndsalon.service.shop.ShopListService;
import com.cndsalon.web.dto.book.DateTimeDTO;

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
	  *
	 */
	@GetMapping("/shop")
	public String showMenu (
			@RequestParam("sCode")String sCode,
			Model model) {
		
		log.info("업체코드 : " + sCode + " - 업체 및 메뉴 조회 후 메뉴선택화면으로 이동");
		String mType = "강아지"; // Menu의 default 값 강아지
		
		model.addAttribute("shop", this.shopService.getShopDetail(sCode));
		model.addAttribute("menu", this.bookingService.getMenuList(sCode, mType));
		
		
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
	  *
	 */
	@GetMapping("/go-booking")
	public String goBooking (
			@RequestParam("sCode")String sCode,
			@RequestParam("mCode")String mCode,
			@RequestParam("mType")String mType,
			Model model) {
		
		log.info("업체 정보 조회");
		model.addAttribute("shop", this.shopService.getShopDetail(sCode));
		
		log.info("메뉴코드 : " + mCode + "로 메뉴조회");
		model.addAttribute("menu", this.bookingService.getMenu(mCode));
		
		log.info("메뉴옵션 : " + mType + "타입으로 옵션조회");
		model.addAttribute("menuOptions", this.bookingService.getMenuOptionList(sCode, mCode, mType));
		
		log.info("디자이너 정보 조회");
		model.addAttribute("designers", this.bookingService.getDesignerList(sCode));

		return "/booking/bookingDetail";
	}

	/**
	  *
	  * <pre>
	  * 개요: 예약화면(bookingDetail.html)에서 사용될 시간요소들을 제어
	  * </pre>
	  * @method createWorkTime
	  * @param 변수명 설명[타입]
	  * @return workTime(영업시간 기준 총 예약 가능 시간목록)
	  * 		notWorkTime(오늘날짜와 현재시간 기준 예약 불가 시간목록)
	  * 		designerWorkTime(예약날짜 기준 이미 예약되어있는 시간목록)
	  * 		Map 타입 timeMap에 담아서 반환 [BookingController]
	  *
	 */
	@ResponseBody
	@GetMapping("/create-work-time")
	public ResponseEntity<Map<String, List<DateTimeDTO>>> createWorkTime(
			@RequestParam("sTime") String sTime,
			@RequestParam("getDate") String getDate,
			@RequestParam("sCode") String sCode,
			@RequestParam("dCode") String dCode){
		
		log.info("1. 매장영업시간 : " + sTime + " / 선택한 날짜 : " + getDate +"-> 기준 예약가능 시간생성 및 현재시간 기준 예약불가 시간생성 후 시간제어");
		log.info("2. 매장번호 : " + sCode + " / 디자이너번호 : " + dCode + "기준 이미 예약되어있는 시간 생성 후 시간제어");
		Map<String, List<DateTimeDTO>> timeMap = this.bookingService.getWorkTimeList(sTime, getDate, sCode, dCode);
		
		return new ResponseEntity<Map<String, List<DateTimeDTO>>>(timeMap, HttpStatus.OK);
	}
	
//	@PostMapping("/realTest")
//	public ResponseEntity<?> insertBooking(){
//		
//		/** TEST **/
//		String bCode = "BOOKING_30";
//		String id = "USER_1";
//		String mCode = "MENU_8";
//		String dCode = "CNDDESIGNER_6";
//		String sCode = "CNDSHOP_61";
//		String bDate = "2020-12-16";
//		String bTime = "09:00";
//		int bBeautyTime = 60;
//		int bPrice =30000;
//		
//		this.bookingService.insertBooking(bCode, id, mCode, dCode, sCode, bDate, bTime, bBeautyTime, bPrice);
//		return new ResponseEntity<>("{}", HttpStatus.OK);
//	}
	
	@PostMapping("/insertBooking")
	public ResponseEntity<?> insertBooking(){
		
		/** TEST **/
		String bCode = "BOOKING_32";
		String id = "USER_1";
		String mCode = "MENU_8";
		String dCode = "CNDDESIGNER_6";
		String sCode = "CNDSHOP_61";
		String bDate = "2020-12-17";
		String bTime = "14:30";
		int bBeautyTime = 60;
		int bPrice =30000;
		
//		LocalDate bDate2 = LocalDate.parse(bDate);
//		LocalTime bTime2 = LocalTime.parse(bTime);
		
		Booking booking = new Booking();
		booking.setBCode(bCode);
		booking.setId(id);
		booking.setMCode(mCode);
		booking.setDCode(dCode);
		booking.setSCode(sCode);
		booking.setBDate(bDate);
		booking.setBTime(bTime);
		booking.setBBeautyTime(bBeautyTime);
		booking.setBPrice(bPrice);
		
		
		this.bookingService.insertBooking(booking);
		return new ResponseEntity<>("{}", HttpStatus.OK);
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
