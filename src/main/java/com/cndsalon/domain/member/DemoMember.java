package com.cndsalon.domain.member;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class DemoMember {
	

	private String id;
	private String pwd;
	private String name;
	private String zip;
	private String address;
	private String phone;
	private String email;
	

}
