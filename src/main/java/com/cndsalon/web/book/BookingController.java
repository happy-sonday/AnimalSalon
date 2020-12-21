package com.cndsalon.web.book;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cndsalon.domain.book.Booking;
import com.cndsalon.repository.book.BookingRepository;
import com.cndsalon.service.book.BookingService;
import com.cndsalon.service.shop.ShopListService;
import com.cndsalon.web.dto.book.BookingDTO;
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
		model.addAttribute("designers", this.shopService.getShopDesignerInfo(sCode));
		
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
		
		log.info("매장영업시간 : " + sTime + " / 선택한 날짜 : " + getDate +"-> 기준 예약가능 시간생성 및 현재시간 기준 예약불가 시간생성 후 시간제어");
		log.info("매장번호 : " + sCode + " / 디자이너번호 : " + dCode + "-> 기준 이미 예약되어있는 시간 생성 후 시간제어");
		Map<String, List<DateTimeDTO>> timeMap = this.bookingService.getWorkTimeList(sTime, getDate, sCode, dCode);
		
		return new ResponseEntity<Map<String, List<DateTimeDTO>>>(timeMap, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/check-available-time")
	public ResponseEntity<Boolean> checkBookingTime(
			@RequestParam("sumB") int sumB,
			@RequestParam("selectedTime") String selectedTime,
			@RequestParam("xTimeList[]") List<String> xTimeList){
		
		log.info("선택한 시간 : " + selectedTime + "에서 소요시간" + sumB + "분을 더한시간이 예약시간 리스트에 겹치지 않는 지 확인");
		
		Boolean status = this.bookingService.checkAvailableTime(sumB, selectedTime, xTimeList);
		
		System.out.println("결과는 ? " + status);
		return ResponseEntity.ok(status);
	}
	
	//@ResponseBody
	//@GetMapping(value = "/make-booking", produces = "application/json; charset=UTF-8")
	
	@ResponseBody
	@RequestMapping(value = "/make-booking", method = {RequestMethod.GET}, produces = "application/json; charset=UTF-8")
	public ResponseEntity<?> insertBooking(
			 BookingDTO bookingDTO
			
			){
			
	
			log.info("예약 값 : " + bookingDTO.toString());
//			Booking booking = Booking.builder()
//					.id(obj.id).mCode(mCode).dCode(dCode).sCode(sCode)
//					.bBeautyTime(bBeautyTime).bPrice(bPrice).build();
//			booking.setBDate(bDate);
//			booking.setBTime(bTime);
			
//			this.bookingService.insertBooking(booking);
			
//		return new ResponseEntity<>("{}", HttpStatus.CREATED);
			return new ResponseEntity<>("{}", HttpStatus.OK);
	}
	
	
	
	
//	@PostMapping("/insertBooking")
//	public ResponseEntity<?> insertBooking(){
//		/** TEST **/
//		String id = "USER_1";
//		String mCode = "MENU_1";
//		String dCode = "CNDDESIGNER_37";
//		String sCode = "CNDSHOP_37";
//		String bDate = "2020-12-20";
//		String bTime = "09:00";
//		int bBeautyTime = 30;
//		int bPrice = 30000;
//		
//		Booking booking = new Booking();
//		booking.setId(id);
//		booking.setMCode(mCode);
//		booking.setDCode(dCode);
//		booking.setSCode(sCode);
//		booking.setBDate(bDate);
//		booking.setBTime(bTime);
//		booking.setBBeautyTime(bBeautyTime);
//		booking.setBPrice(bPrice);
//		
//		this.bookingService.insertBooking(booking);
//		
//		/** TEST **/
//		String id1 = "USER_1";
//		String mCode1 = "MENU_1";
//		String dCode1 = "CNDDESIGNER_97";
//		String sCode1 = "CNDSHOP_37";
//		String bDate1 = "2020-12-20";
//		String bTime1 = "10:00";
//		int bBeautyTime1 = 60;
//		int bPrice1 = 30000;
//		
//		booking = null;
//		booking = new Booking();
//		booking.setId(id1);
//		booking.setMCode(mCode1);
//		booking.setDCode(dCode1);
//		booking.setSCode(sCode1);
//		booking.setBDate(bDate1);
//		booking.setBTime(bTime1);
//		booking.setBBeautyTime(bBeautyTime1);
//		booking.setBPrice(bPrice1);
//		
//		this.bookingService.insertBooking(booking);
//		
//		/** TEST **/
//		String id2 = "USER_1";
//		String mCode2 = "MENU_1";
//		String dCode2 = "CNDDESIGNER_157";
//		String sCode2 = "CNDSHOP_37";
//		String bDate2 = "2020-12-20";
//		String bTime2 = "11:00";
//		int bBeautyTime2 = 60;
//		int bPrice2 = 30000;
//		
//		booking = null;
//		booking = new Booking();
//		booking.setId(id2);
//		booking.setMCode(mCode2);
//		booking.setDCode(dCode2);
//		booking.setSCode(sCode2);
//		booking.setBDate(bDate2);
//		booking.setBTime(bTime2);
//		booking.setBBeautyTime(bBeautyTime2);
//		booking.setBPrice(bPrice2);
//		
//		this.bookingService.insertBooking(booking);
//		
//		/** TEST **/
//		String id3 = "USER_1";
//		String mCode3 = "MENU_1";
//		String dCode3 = "CNDDESIGNER_37";
//		String sCode3 = "CNDSHOP_37";
//		String bDate3 = "2020-12-20";
//		String bTime3 = "13:00";
//		int bBeautyTime3 = 180;
//		int bPrice3 = 30000;
//		
//		booking = null;
//		booking = new Booking();
//		booking.setId(id3);
//		booking.setMCode(mCode3);
//		booking.setDCode(dCode3);
//		booking.setSCode(sCode3);
//		booking.setBDate(bDate3);
//		booking.setBTime(bTime3);
//		booking.setBBeautyTime(bBeautyTime3);
//		booking.setBPrice(bPrice3);
//		
//		this.bookingService.insertBooking(booking);
//		
//		/** TEST **/
//		String id4 = "USER_1";
//		String mCode4 = "MENU_1";
//		String dCode4 = "CNDDESIGNER_97";
//		String sCode4 = "CNDSHOP_37";
//		String bDate4 = "2020-12-20";
//		String bTime4 = "14:00";
//		int bBeautyTime4 = 120;
//		int bPrice4 = 30000;
//		
//		booking = null;
//		booking = new Booking();
//		booking.setId(id4);
//		booking.setMCode(mCode4);
//		booking.setDCode(dCode4);
//		booking.setSCode(sCode4);
//		booking.setBDate(bDate4);
//		booking.setBTime(bTime4);
//		booking.setBBeautyTime(bBeautyTime4);
//		booking.setBPrice(bPrice4);
//		
//		this.bookingService.insertBooking(booking);
//		
//		/** TEST **/
//		String id5 = "USER_1";
//		String mCode5 = "MENU_1";
//		String dCode5 = "CNDDESIGNER_157";
//		String sCode5 = "CNDSHOP_37";
//		String bDate5 = "2020-12-20";
//		String bTime5 = "15:00";
//		int bBeautyTime5 = 90;
//		int bPrice5 = 30000;
//		
//		booking = null;
//		booking = new Booking();
//		booking.setId(id5);
//		booking.setMCode(mCode5);
//		booking.setDCode(dCode5);
//		booking.setSCode(sCode5);
//		booking.setBDate(bDate5);
//		booking.setBTime(bTime5);
//		booking.setBBeautyTime(bBeautyTime5);
//		booking.setBPrice(bPrice5);
//		
//		this.bookingService.insertBooking(booking);
//	
//		return new ResponseEntity<>("{}", HttpStatus.OK);
//	}
	
	@GetMapping("/selectBooking")
	public ResponseEntity<List<Booking>> selectBooking(
			@RequestParam("sCode") String sCode,
			@RequestParam("dCode") String dCode,
			@RequestParam("bDate") String date){
		LocalDate bDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		/** TEST **/
		List<Booking> booking = this.bookingService.selectBooking(dCode, sCode, bDate);
		return ResponseEntity.ok(booking);
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
