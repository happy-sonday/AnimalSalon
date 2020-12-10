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
	    private Long id;

	    @Column(nullable = false)
	    private String name;

	    @Column(nullable = false)
	    private String email;

	    @Column
	    private String profile;

	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private Role role;

	    @Builder
	    public Member(String name, String email, String profile, Role role) {
	        this.name = name;
	        this.email = email;
	        this.profile = profile;
	        this.role = role;
	    }

	    public Member update(String name, String profile) {
	        this.name = name;
	        this.profile = profile;

	        return this;
	    }

	    public String getRoleKey() {
	        return this.role.getKey();
	    }
	
	
	
	/*
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	
	private String pwd;
	private String name;
	private String zip;
	private String address;
	private String phone;
	private String email;
	private String nickname;
	private Integer  actNickname;
	
	@Enumerated(EnumType.STRING)
	@Column
	private Role role;
	private Timestamp indate;
	private Integer agreeLocation;
	private Integer agreePromotion;
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
	*/
}