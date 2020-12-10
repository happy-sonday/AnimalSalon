package com.cndsalon.service.member;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cndsalon.domain.member.DemoMember;
import com.cndsalon.repository.member.MemberDemoRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberCRUDServiceImpl implements MemberCRUDService{

	@Autowired
	private MemberDemoRepository repository;
	
	@Transactional
	@Override
	public void insert(DemoMember reqDto) {
		
			log.debug("memberDemoRepository MemberService insert(): {}", reqDto);
			repository.save(reqDto);		
	}

	@Transactional
	@Override
	public void update(DemoMember reqDto) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	@Override
	public String delete(DemoMember reqDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public List<DemoMember> allList(DemoMember reqDto) {
		// TODO Auto-generated method stub
		return null;
	}

}
