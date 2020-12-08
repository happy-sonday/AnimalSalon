package com.cndsalon.domain.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table
public class CustomerRegister {

	
	//회원아이디
	@Id @GeneratedValue
	@Column(name = "CUSTOMER_ID")
	private Long id;
	
	
	private String pwd;//비밀번호
	private String name;//이름
	private String zip;//우편번호
	private String address;//주소
	private String phone;//휴대전화
	private String email;//이메일
	private String nickname;//별명
	private String actNickname;//별명사용여부
	private String role;//권한
	private String indate;//가입일  sysdate
	private String agreeLocation;//위치정보동의
	private String agreePromotion;//광고수신동의
	private String profile;//프로필사진
	
	
	
	
}
