package com.cndsalon.web.dto.payment;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class AuthData {
	@SerializedName("imp_key")
	private String api_key;
	
	@SerializedName("imp_secret")
	private String api_secret;
	
	public AuthData(String api_key, String api_secret) {
		this.api_key = api_key;
		this.api_secret = api_secret;
	}
}
