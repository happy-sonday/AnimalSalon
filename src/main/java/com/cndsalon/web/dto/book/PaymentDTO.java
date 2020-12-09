package com.cndsalon.web.dto.book;

import java.util.Date;

import lombok.Data;

@Data
public class PaymentDTO {
	private String pCode;			// 결제코드(주문번호)
	private String bCode;			// 예약코드
	private String pg;				// PG사
	private String pMethod;		// 결제수단
	private String pOption;			// 결제옵션
	private Integer pPrice;			// 결제금액
	private String buyerEmail;		// 구매자 이메일
	private String buyerTel;		// 구매자 전화번호
	private Date pDate;				// 결제일자
	private Character pStatus;	// 결제상태 (1 : 결제완료, 2:결제취소)

}
