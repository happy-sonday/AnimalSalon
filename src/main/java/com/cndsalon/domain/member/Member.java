package com.cndsalon.domain.member;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	
	private String pwd;
	private String name;
	private String zip;
	private String address;
	private String phone;
	private String email;
	private String nickname;
	private int act_nickname;
	
	@Enumerated(EnumType.STRING)
	@Column
	private Role role;
	private Timestamp indate;
	private int agreeLocation;
	private int agreePromotion;
	private String profile;

	@Builder
	public Member(String name, String email, String profile, Role role) {
		this.name = name;
		this.email = email;
		this.profile = profile;
		this.role = role;
	}

	public Member update(String name, String picture) {
		this.name = name;
		this.profile = picture;

		return this;
	}

	public String getRoleKey() {
		return this.role.getKey();
	}
}