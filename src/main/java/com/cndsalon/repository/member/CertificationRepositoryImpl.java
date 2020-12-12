package com.cndsalon.repository.member;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;


@Repository
@Slf4j
public class CertificationRepositoryImpl  implements CertificationRepository{

	
	@Autowired
	private SqlSessionTemplate sqlSession;

	
	@Override
	public int findById(String id) {				
		log.debug("findById >>>> value: {}", id);
		int chk=this.sqlSession.getMapper(MemberDemoMapper.class).findById(id);
		
		return chk;
	}

	@Override
	public int findByPhone(String phone) { 
		return (int)this.sqlSession.selectOne("findByPhone", phone);
	}

	@Override
	public int findByEmail(String email) {
	
		return (int)this.sqlSession.selectOne("findByEmail", email);
	}

	@Override
	public  int findByNickname(String nickname) { 
		
		return (int)this.sqlSession.selectOne("findByNickname", nickname);
	}



}
