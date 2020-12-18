//package com.cndsalon.service.member;
//
//import java.io.UnsupportedEncodingException;
//import java.net.URISyntaxException;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//
//import javax.mail.internet.MimeMessage;
//
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.web.client.RestClientException;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//
//import lombok.extern.slf4j.Slf4j;
//
//@SpringBootTest
//@Slf4j
//public class TotalCertificationTest {
//
//	@Autowired
//	CertificationService certificationService;
//		
//	//통합인터페이스: 메일 전송 테스트
//	
//	@Disabled
//	@Test
//	void sendSms() throws InvalidKeyException, RestClientException, JsonProcessingException, UnsupportedEncodingException, NoSuchAlgorithmException, URISyntaxException {
//		//통과
//		String sCode=certificationService.numberGen(6, 2);
//		certificationService.sendSms("01096558120", "애니멀 살롱 본인 인증 암호["+sCode+"} 전송합니다");
//	}
//
//	
//	//통합인터페이스: 문자 전송 테스트
//
//	@Disabled
//	@Test
//	void sendMail() {//통과
//		log.debug("mail send 실행");
//		
//		
//		String sCode=certificationService.numberGen(5, 2); MimeMessage
//		setSMTP=certificationService.setSMTP("네이버 또는 구글", "발신자 이메일 계정","발신자 이메일 비밀번호");
//																						//내역에 노출될 수 있으니 삭제 확인 후 푸시 할것 
//		String	content="안녕하세요 본인인증 번호는["+sCode+"]입니다";
//		
//		certificationService.sendEmailWith(setSMTP, "수신자 계정", "메일 제목", "메일 본문");
//		
//
//	}
//}
