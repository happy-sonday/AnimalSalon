package com.cndsalon.web.dto.payment;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public class AccessToken {
	@SerializedName("access_token")
	String token;
	
	@SerializedName("expired_at")
	int expired_at;
	
	@SerializedName("now")
	int now;
}
