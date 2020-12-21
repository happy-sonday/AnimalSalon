package com.cndsalon.web.dto.payment;

import java.math.BigDecimal;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class CancelData {
	@SerializedName("imp_uid")
	private String imp_uid;
	
	@SerializedName("merchant_uid")
	private String merchant_uid;
	
	@SerializedName("cancel_request_amount")
	private BigDecimal amount;
	
	@SerializedName("reason")
	private String reason;
	
	public CancelData() {}
	
	public CancelData(String uid, boolean imp_uid_or_not) {
		if ( imp_uid_or_not ) {
			this.imp_uid = uid;
		} else {
			this.merchant_uid = uid;
		}
	}
	
	public CancelData(String uid, boolean imp_uid_or_not, BigDecimal amount) {
		this(uid, imp_uid_or_not);
		this.amount = amount;
	}
}
