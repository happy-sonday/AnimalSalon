package com.cndsalon.repository.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cndsalon.domain.member.DemoMember;
import com.cndsalon.service.member.CertificationService;
import com.cndsalon.service.member.MemberCRUDService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class registerMemberTest_hashedPwd {
	
	
	@Autowired
	MemberCRUDService service;
	
	@Autowired
	CertificationService chkService;
	
	@Test
	public void integrationRegisterMemberTest() {
		
		DemoMember member = new DemoMember();
		
		member.setId("enable");
		member.setPassword("enable123");
		member.setName("enable");//이름    
		member.setZip("34323");//우편번호   
		member.setAddress("enabled동 접근가능시 세이호오");//집주소
		member.setPhone("01043453433");//휴대번호 
		member.setEmail("enable@naver.com");//이메일		
		member.setNickname("접근가능이");//별명
		member.setAcceptAd(1);
		member.setActNickname(1);	
		
		
		service.insert(member);
		
		//12월 18일 security 적용 관련 회원가입 등록단위테스트 등록 통과
		
		
		
	}
	

}
