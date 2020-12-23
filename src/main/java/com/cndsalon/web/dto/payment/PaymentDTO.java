package com.cndsalon.web.dto.payment;

import java.math.BigDecimal;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * 결제정보 DTO
 * 
 * @author CWLEE
 * @version 1.0
 * 
 * @param impUid 아임포트 거래 고유번호
 * @param merchantUid 고유 주문번호 (=결제코드)
 * @param pgProvider PG사
 * @param payMethod 결제수단
 * @param paidAmount 결제금액
 * @param status 결제상태[ paid(결제완료), cancelled(결제취소, 부분취소포함) ]
 * @param paidAt 결제승인시각
 * @param name 주문명
 * @param buyerName 주문자이름
 * @param buyerEmail 주문자 Email
 * @param buyerTel 주문자 연락처
 * @param buyerAddr 주문자 주소
 * @param buyerPostcode 주문자 우편번호
 */
@Data
public class PaymentDTO {
	@SerializedName("imp_uid")
	private String impUid;
	
	@SerializedName("merchant_uid")
	private String merchantUid;
	
	@SerializedName("pg_provider")
	private String pgProvider;
	
	@SerializedName("pay_method")
	private String payMethod;
	
	@SerializedName("amount")
	private BigDecimal paidAmount;
	
	@SerializedName("status")
	private String status;
	
	@SerializedName("paid_at")
	private Integer paidAt;
	
	@SerializedName("name")
	private String name;
	
	@SerializedName("buyer_name")
	private String buyerName;
	
	@SerializedName("buyer_email")
	private String buyerEmail;
	
	@SerializedName("buyer_tel")
	private String buyerTel;
	
	@SerializedName("buyer_addr")
	private String buyerAddr;
	
	@SerializedName("buyer_postcode")
	private String buyerPostcode;
}
