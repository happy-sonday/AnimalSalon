package com.cndsalon.repository.member;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cndsalon.service.member.CertificationService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class MemberMybatisTest {
	
	
	@Autowired
	CertificationService service;
	//아이디 중복검사 및 mybatis 중복여부 확인
	@Test
	public void overlapCheckingTest() {
		
		//assertEquals(18, service.findById("mrson1106"));
		
		int chk=service.findById("mrson1106");
		
		log.debug("값 확인 : {}",chk);//실행확인 구웃
		assertEquals(18, chk);
			
		
	}

}
