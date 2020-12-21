package com.cndsalon.web.dto.payment;

import java.math.BigDecimal;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class PaymentDTO {
	@SerializedName("imp_uid")
	private String impUid;				// 아임포트 거래 고유번호
	
	@SerializedName("merchant_uid")
	private String merchantUid;		// 고유 주문번호 (=결제코드)
	
	@SerializedName("pg_provider")
	private String pgProvider;			// PG사
	
	@SerializedName("pay_method")
	private String payMethod;			// 결제수단
	
	@SerializedName("amount")
	private BigDecimal paidAmount;		// 결제금액
	
	@SerializedName("status")
	private String status;					// 결제상태[ paid(결제완료), cancelled(결제취소, 부분취소포함) ]
	
	@SerializedName("paid_at")
	private Integer paidAt;				// 결제승인시각
	
	@SerializedName("name")
	private String name;					// 주문명
	
	@SerializedName("buyer_name")
	private String buyerName;			// 주문자이름
	
	@SerializedName("buyer_email")
	private String buyerEmail;			// 주문자 Email
	
	@SerializedName("buyer_tel")
	private String buyerTel;				// 주문자 연락처
	
	@SerializedName("buyer_addr")
	private String buyerAddr;			// 주문자 주소
	
	@SerializedName("buyer_postcode")
	private String buyerPostcode;	// 주문자 우편번호
}
