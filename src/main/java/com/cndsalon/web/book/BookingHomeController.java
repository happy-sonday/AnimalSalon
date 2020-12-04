package com.cndsalon.web.book;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BookingHomeController {

	/**
	 * 예약내역관련
	 * */
	// 메인헤더 '예약' 버튼 클릭 시 or 예약홈서브헤더(bookingSubHeader.html) '전체'버튼 클릭 시
	@GetMapping("/bookinghome/all")
	public String getBookingAll() throws Exception{
		return "/booking/bookingHome";
	}
	
	// 예약홈서브헤더(bookingSubHeader.html) '예약'버튼 클릭 시
	@GetMapping("/bookinghome/ing")
	public String getBookingIng() {
		return "/booking/bookingHomeIng";
	}
	// 예약홈서브헤더(bookingSubHeader.html) '완료'버튼 클릭 시	
	@GetMapping("/bookinghome/complete")
	public String getBookingComplete() {
		return "/booking/bookingHomeComplete";
	}
	// 예약홈서브헤더(bookingSubHeader.html) '취소'버튼 클릭 시	
	@GetMapping("/bookinghome/cancel")
	public String getBookingCancel() {
		return "/booking/bookingHomeCancel";
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
