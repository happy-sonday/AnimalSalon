package com.cndsalon.repository.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cndsalon.domain.member.Member;


public interface MemberRepository extends JpaRepository<Member, Long> {
		
	Optional<Member> findByEmail(String email);//소셜로그인으로 반환되는 값 중 email을 통해 가입여부 판단

}
