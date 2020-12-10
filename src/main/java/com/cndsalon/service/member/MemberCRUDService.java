package com.cndsalon.service.member;

import java.util.List;

import com.cndsalon.domain.member.DemoMember;

public interface MemberCRUDService {

	//member등록
	public void insert(DemoMember reqDto);
	
	//member 변경
	public void update(DemoMember reqDto);
	
	//member 삭제
	public String delete(DemoMember reqDto);
	
	//member 전체 리스트 조회	
	public List<DemoMember> allList(DemoMember reqDto);
}
