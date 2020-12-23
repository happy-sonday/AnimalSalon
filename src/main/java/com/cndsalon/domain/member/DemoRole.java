package com.cndsalon.domain.member;

import org.apache.ibatis.javassist.SerialVersionUID;
import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;
import lombok.Setter;


public class DemoRole implements GrantedAuthority{

	
	/**
	 * serialVersionUID는
	 * controll + 숫자 1로 생성
	 * getter setter는 UID에 직접 접근 못하기 위해 lombok 사용을 안함
	 */
	private static final long serialVersionUID = -1415354262577848391L;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	private String id;
	private String role;
	
	@Override
	public String getAuthority() {		
		return this.role;
	}

}
