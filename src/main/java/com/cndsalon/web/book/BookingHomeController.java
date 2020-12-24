package com.cndsalon.web.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cndsalon.domain.book.Booking;
import com.cndsalon.service.book.BookingHomeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/bookinghome")
public class BookingHomeController {

	@Autowired
	private BookingHomeService bookingHomeSerivce;
	
	/**
	  *
	  * <pre>
	  * 개요: 예약상태에 따른 예약내역을 반환하는 메서드
	  * </pre>
	  * @method getBookingAll
	  * @param id 테스트용 추후 폐기예정 [String]
	  * @param bStatus 예약상태 **all 입력 시 삭제된 내역을 제외하고 모두 반환 [String]
	  * @return 예약상태에 따른 예약내역 리스트 반환 및 bookingHome화면으로 이동 [BookingHomeController]
	  *
	 */
	@GetMapping("/status/{bStatus}")
	public String getBookingViewList(
			String id, 
			@PathVariable("bStatus")String bStatus, 
			Model model) throws Exception{

		// TestId
		id = "testId";
		log.info(bStatus + "상태의 예약목록 조회");
		model.addAttribute("bView", this.bookingHomeSerivce.getBookingViewList(id, bStatus));
		return "/booking/bookingHome";
	}
	
	
	
	/**
	  *
	  * <pre>
	  * 개요: 예약 상태를 수정하는 메서드
	  * </pre>
	  * @method updateBooking
	  * @param 수정할 컬럼값을 받아오는 booking [Booking]
	  *
	 */
	@ResponseBody
	@PutMapping("/bookings/{bCode}")
	public ResponseEntity<?> updateBooking(
			@PathVariable("bCode") Long bCode,
			@RequestBody Booking booking
			) {
		
		log.info("bCode : " + booking.toString() + "의 상태 업데이트");
		this.bookingHomeSerivce.updateBooking(bCode, booking.getBStatus(), booking.getBCancelReason());
		return new ResponseEntity<>("{}", HttpStatus.OK);
	}
	

	// 진행중예약내역화면(bookingHomeIng.html)'시간변경'버튼 클릭 시
	@GetMapping("/timeChangeView.do")
	public String timeChangeView() {
		return "/booking/sub/bookingTimeChange";
	}
	
	// 
	/**
	  *
	  * <pre>
	  * 개요: 예약내역화면(bookingHomeIng.html) '예약취소'버튼 클릭 시
	  * </pre>
	  * @method 해당 예약번호 전송 및 window.open  
	  * @param bCode 예약번호 [Long]
	  * @return 예약취소화면 반환 [BookingHomeController]
	  *
	 */
	@GetMapping("/cancelView.do")
	public String cancelView(@RequestParam("bCode") Long bCode, Model model) {
		model.addAttribute("bCode", bCode);
		return "/booking/sub/bookingCancel";
	}
	
}
