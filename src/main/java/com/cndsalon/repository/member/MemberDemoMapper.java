package com.cndsalon.repository.member;

import org.springframework.web.multipart.MultipartFile;

import com.cndsalon.domain.member.DemoMember;

public interface MemberDemoMapper {


	//회원가입 정보 저장
	public void save(DemoMember demoMember);
	
	//프로필사진 업데이트
	public void updateProfile(String enFileName);

	//아이디 중복확인 메서드
	public int findById(String id);
	
}
