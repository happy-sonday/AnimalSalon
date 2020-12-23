package com.cndsalon.web.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cndsalon.service.book.BookingHomeService;

import jdk.internal.org.jline.utils.Log;
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
	 * 예약내역 각 버튼 관련
	 * */
	// 진행중예약내역화면(bookingHomeIng.html)'시간변경'버튼 클릭 시
	@GetMapping("/timeChangeView.do")
	public String timeChangeView() {
		return "/booking/sub/bookingTimeChange";
	}
	
	// 진행중예약내역화면(bookingHomeIng.html)'예약취소'버튼 클릭 시
	@GetMapping("/cancelView.do")
	public String cancelView() {
		return "/booking/sub/bookingCancel";
	}
	
	// 전체,완료,취소 예약내역화면에서 '내역삭제' 버튼 클릭 시 
	@DeleteMapping("/bookings/{예약번호}")
	@ResponseBody
	public ResponseEntity<?> deleteBooking(){
		return new ResponseEntity<>("{}", HttpStatus.OK);
	}
	
	// 확인용)))) 예약취소화면(bookingCancel.html)예약취소버튼 클릭 시
	@PutMapping("/bookingCancel.do")
	public String bookingCancel() {
		return null;
	}
	
	// 확인용)))) 예약시간변경화면(bookingTimeChange.html)에서 예약시간변경버튼 클릭 시
		@PutMapping("/bookingTimeChange.do")
		public String bookingTimeChage() {
			return null;
		}
	
}
