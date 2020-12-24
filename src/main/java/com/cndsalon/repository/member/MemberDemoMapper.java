package com.cndsalon.repository.member;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.cndsalon.domain.member.CustomUserDetails;
import com.cndsalon.domain.member.DemoMember;
import com.cndsalon.web.dto.member.DemoMemberDto;

public interface MemberDemoMapper {


	//회원가입 정보 저장
	public void save(DemoMember demoMember);
	
	//프로필사진 업데이트
	public void updateProfile(String enFileName);

	//아이디 중복확인 메서드
	public int findById(String id);

	//DB에 아이디 조건으로 해쉬처리된 암호 가져오기
	public String hashedPwdById(@Param("id")String id);

	public void insert(DemoMemberDto dto);

	public DemoMemberDto findbyEmail(String username);

	
	//loginRepository loadUserDatails를 위한 DB 추출
	public CustomUserDetails getOne(String username);
	
	
	
	
	
}
