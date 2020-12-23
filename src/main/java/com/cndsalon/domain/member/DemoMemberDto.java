package com.cndsalon.domain.member;

import java.security.Timestamp;

import com.cndsalon.domain.member.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DemoMemberDto {
	
	  private String email;
	  private String password;
	  private Timestamp createDate;
	  private String role;
	
}
