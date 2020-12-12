package com.cndsalon.service.member;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonProcessingException;

@SpringBootTest
public class SmsServiceTest {
	
	@Autowired
	private SmsService smsService;
	
	@Test
	void sendSms() throws InvalidKeyException, RestClientException, JsonProcessingException, UnsupportedEncodingException, NoSuchAlgorithmException, URISyntaxException {
		smsService.sendSms("01096558120", "애니멀 살롱 본인 인증 암호 전송합니다");
	}
	
	//test 추가

}
