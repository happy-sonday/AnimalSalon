package com.cndsalon.web.member;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.cndsalon.domain.member.DemoMember;
import com.cndsalon.service.member.MemberCRUDService;

import lombok.extern.slf4j.Slf4j;




@RestController
@Slf4j
public class MemberCRUDController {
	
	
	 @Autowired
	 private MemberCRUDService service;
	
	//회원가입 양식 저장 요청
	@PostMapping("/member/join")
	public void saveMemberInfo(@RequestBody DemoMember demoMember) {
		//idx 를 int반환받아서 조회해서 해당 welcome으로 넘기는거 어떻가 이중 ajax통신

		log.info("MemberCRUDController saveMemberInfo() 실행 : DemoMember : {}", demoMember);		
		service.insert(demoMember);		
	
	}

}
