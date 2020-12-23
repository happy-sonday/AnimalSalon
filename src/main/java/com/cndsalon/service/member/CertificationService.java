package com.cndsalon.service.member;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.mail.internet.MimeMessage;

import org.springframework.web.client.RestClientException;

import com.cndsalon.web.dto.member.SendSmsResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface CertificationService {

	//////////////////////////////////////////////////////회원가입 유효성 검사	
	//유효성 검사
	//아이디 중복 검사
	public int findById(String id);
	
	//핸드폰 중복 검사
	public int findByPhone(String phone);
	
	
	//이메일 중복검사
	public int findByEmail(String email);
	
	//별명 중복 검사
	public int findByNickname(String nickname);
	
	//난수 발생 메서드
	public String numberGen(int len, int dupCd );
	
	
	
	////////////////////////////////////////////////////////메일 서비스
	//SMTP 설정
	public MimeMessage setSMTP(String NaverOrGoole, String senderEmail, String senderPassword);
	
	//메일 발송 실행
	public void sendEmailWith(MimeMessage setSMTP, String receiver, String title, String content ) ;
	
	
	
	////////////////////////////////////////////////////////문자 서비스 네이버 클라우드 플랫폼 SENS
	//이슈: 알뜰폰은 발송은 성공하나 내용 확인 못함
	
	///접근 시크릿키 등록  ->> 추후에 각 사용자 키 받는 인자 넣을것
	public String makeSignature(Long time) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException; 
	
	//문자 발송 실행 메서드
	public SendSmsResponseDto sendSms(String recipientPhoneNumber, String content) throws JsonProcessingException, InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, RestClientException, URISyntaxException ;

	public String loginChk(String id, String pwd);
	
	
	
	
	////////////////////////////////////////////////////////
	
}
