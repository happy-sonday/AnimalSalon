package com.cndsalon.repository.member;

import com.cndsalon.domain.member.DemoMember;

public interface MemberDemoRepository {
	
	
	public void save(DemoMember demoMember);
	
	public DemoMember findOne(String id);
	
				
	
	

}
