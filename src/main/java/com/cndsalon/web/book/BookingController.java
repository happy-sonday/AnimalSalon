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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cndsalon.domain.book.Booking;
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
	
	/**
	  *
	  * <pre>
	  * 개요: shop_detail.html에서 예약하기 클릭 시
	  * </pre>
	  * @method moveBookingMenu
	  * @return 해당 업체, 강아지 타입메뉴 조회 / bookingMenu.html 반환 [BookingController]
	  *
	 */
	@GetMapping("/shop/{sCode}/type/{mType}")
	public String showMenu (
			@PathVariable("sCode")String sCode,
			@PathVariable("mType")String mType,
			Model model) {
		
		log.info("업체코드 / 메뉴타입 : " + sCode + " / " + mType);
		
		model.addAttribute("shop", this.shopService.getShopDetail(sCode));
		model.addAttribute("menu", this.bookingService.getMenuList(sCode, mType));
		
		return "/booking/bookingMenu";
	}
	
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
	
	/**
	  *
	  * <pre>
	  * 개요: bookingMenu.html 에서 해당메뉴 예약 클릭 시
	  * </pre>
	  * @method goBooking
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
	
	/**
	  *
	  * <pre>
	  * 개요: 예약화면 시간버튼 클릭 시 기존 예약시간과 겸침 유무 판단하는 메서드
	  * </pre>
	  * @method checkBookingTime
	  * @param sumB 소요시간 [int]
	  * @param selectedTime 예약하려는 시간 [String]
	  * @param xTimeList 기존 예약목록 시간 [List<String>]
	  * @return 겸침유무 Boolean 반환 [BookingController]
	  *
	 */
	@ResponseBody
	@GetMapping("/check-available-time")
	public ResponseEntity<Boolean> checkBookingTime(
			@RequestParam("sumB") int sumB,
			@RequestParam("selectedTime") String selectedTime,
			@RequestParam("xTimeList[]") List<String> xTimeList){
		
		log.info("선택한 시간 : " + selectedTime + "에서 소요시간 " + sumB + "분을 더한시간이 예약시간 리스트에 겹치지 않는 지 확인");
		
		Boolean status = this.bookingService.checkAvailableTime(sumB, selectedTime, xTimeList);
		
		System.out.println("결과는 ? " + status);
		return ResponseEntity.ok(status);
	}
	
	/**
	  *
	  * <pre>
	  * 개요: bookingDetail.html 에서 예약 시 예약 등록하는 메서드
	  * </pre>
	  * @method insertBooking
	  * @param bookingDTO[타입]
	  * @return 설명 [BookingController]
	  *
	 */
	@ResponseBody
	@PostMapping("/make-booking")
	public ResponseEntity<?> insertBooking(
			@RequestBody BookingDTO bookingDTO
			){
			log.info("예약내용 : " + bookingDTO.toString());
			
			Booking booking = Booking.builder()
					.id(bookingDTO.getId())
					.mCode(bookingDTO.getMcode())
					.dCode(bookingDTO.getDcode())
					.sCode(bookingDTO.getScode())
					.bBeautyTime(bookingDTO.getBeautytime())
					.bPrice(bookingDTO.getPrice())
					.build();
			booking.setBDate(bookingDTO.getBdate());
			booking.setBTime(bookingDTO.getBtime());
			
			this.bookingService.insertBooking(booking);
		
		return new ResponseEntity<>("{}", HttpStatus.CREATED);
	}

	
	
}
