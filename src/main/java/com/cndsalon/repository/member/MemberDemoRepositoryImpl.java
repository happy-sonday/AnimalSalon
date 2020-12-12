package com.cndsalon.repository.member;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.cndsalon.domain.member.DemoMember;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class MemberDemoRepositoryImpl implements MemberDemoRepository{

	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	
	@Override
	public void save(DemoMember demoMember) {
		
		log.debug("MemberDemoRepositoryImple >>>> save() DemoMember {}", demoMember);
		this.sqlSession.getMapper(MemberDemoMapper.class).save(demoMember);
	}

	@Override
	public DemoMember findOne(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateProfile(String enFileName) {
		
		
		this.sqlSession.getMapper(MemberDemoMapper.class).updateProfile(enFileName);		
	}
	
	

}
