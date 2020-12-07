package com.cndsalon.web.dto.member;


import java.sql.Timestamp;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SendSmsResponseDto {

	private String statusCode;
	private String statusName;
	private String requestId;
	private Timestamp requestTime;
}
