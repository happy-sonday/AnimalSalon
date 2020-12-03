package com.cndsalon.web.dto.book;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class BookingDTO {
	private Integer bookingCode;						// 예약코드
	private String userId;									// 예약자ID
	private Integer shopCode;							// 매장코드
	private Integer menuCode;							// 메뉴코드
	private Integer optionCode;							// 옵션코드
	private Integer designerCode;						// 디자이너코드
	private LocalDate bookingDate;						// 일정(년월일)
	private LocalTime bookingTime;						// 일정(시간)
	private LocalTime totalBeautyTime;				// 총소요시간
	private Integer totalPrice;								// 총 금액
	private Character bookingStatus;					// 예약상태
	private String cancelReason;							// 취소사유
}
