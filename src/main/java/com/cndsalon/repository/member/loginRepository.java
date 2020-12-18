package com.cndsalon.repository.member;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cndsalon.domain.member.CustomUserDetails;
import com.cndsalon.web.dto.member.DemoMemberDto;


@Repository
public class loginRepository {
	
	
	@Autowired
	SqlSessionTemplate sTemplate;
	
	public void save (DemoMemberDto dto) {
		
		sTemplate.getMapper(MemberDemoMapper.class).insert(dto);
	}

	public DemoMemberDto findByEmail(String username) {
		
		return sTemplate.getMapper(MemberDemoMapper.class).findbyEmail(username);
	}
	
	public CustomUserDetails getOne(String username) {
		
		
		return sTemplate.getMapper(MemberDemoMapper.class).getOne(username);
	}
	
	

}
