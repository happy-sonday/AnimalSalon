package com.cndsalon.repository.member;

import org.springframework.web.multipart.MultipartFile;

import com.cndsalon.domain.member.DemoMember;

public interface MemberDemoRepository {
	
	
	public void save(DemoMember demoMember);
	
	public DemoMember findOne(String id);

	//회원 사진 수정
	public void updateProfile(String enFileName);
	
				
	
	

}
