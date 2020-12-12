package com.cndsalon.web.member;


import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ServerWebInputException;

import com.cndsalon.domain.member.DemoMember;
import com.cndsalon.service.member.MemberCRUDService;


import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MemberCRUDController {

	@Autowired
	private MemberCRUDService service;


	// 회원가입 양식 저장 요청
	@PostMapping("/member/join")
	public void saveMemberInfo(@RequestBody DemoMember demoMember) {
		// idx 를 int반환받아서 조회해서 해당 welcome으로 넘기는거 어떻가 이중 ajax통신

		log.info("MemberCRUDController saveMemberInfo() 실행 :{}", demoMember);
		service.insert(demoMember);

	}

	//파일 업로드 
		@PostMapping("/member/profile")
		public void updateProfile(MultipartFile pFile){		
			
			service.updateProfile(pFile);			
			
		}
		
	
	

}
