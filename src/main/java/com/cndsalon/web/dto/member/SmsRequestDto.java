package com.cndsalon.web.dto.member;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Service
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SmsRequestDto {

	private String type;	
	private String contentType;
	private String countryCode;
	private String from;
	private String content;
	
	private List<MessageRequestDto> messages;
	
	
	
	
}
