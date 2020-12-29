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
	
	@SerializedName("amount")
	private BigDecimal amount;
	
	@SerializedName("reason")
	private String reason;
	
	public CancelData() {}
	
}
