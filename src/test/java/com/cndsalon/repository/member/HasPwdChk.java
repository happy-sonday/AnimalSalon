package com.cndsalon.repository.member;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cndsalon.domain.member.DemoMember;
import com.cndsalon.service.member.CertificationService;
import com.cndsalon.service.member.MemberCRUDService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class HasPwdChk {

	@Autowired
	MemberCRUDService service;
	
	@Autowired
	CertificationService chkService;
	
	
	//해쉬 암호화 저장 테스트	
	@Test
	public void hashPwdTest() {
		
		DemoMember member = new DemoMember();
		
		member.setAddress("address");
		member.setId("happysonday06");
		member.setPassword("sonday123");
		member.setName("손우섭");
		member.setEmail("kakaoman123@gmail.com");
		member.setPhone("01082827942");
		member.setZip("1245");
		
		service.insert(member);		
		
	}
	
	//저장 및 해시 암호화 확인
	@Test
	public void loginChk() {
		
		String id="happysonday06";
		String pwd="sonday123";
		if(chkService.findById(id)>0) {
		
			log.info("ID 존재 및 해시 암호 체크");
			chkService.loginChk(id,pwd);
		}
		
		
		
	}
	
	
	
	
	
	
}
