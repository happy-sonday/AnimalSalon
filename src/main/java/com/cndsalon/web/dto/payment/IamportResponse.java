package com.cndsalon.web.dto.payment;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public class IamportResponse<T> {
	@SerializedName("code")
	int code;
	
	@SerializedName("message")
	String message;
	
	@SerializedName("response")
	T response;
}
