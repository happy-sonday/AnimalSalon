package com.cndsalon.domain.shop;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Embeddable
@NoArgsConstructor
public class ShopInfoId implements Serializable{

	 @EqualsAndHashCode.Include
	 @Column(name = "S_CODE")
	 private String sCode;
	 
	 public ShopInfoId(String sCode) {
		 this.sCode = sCode;
	 }
}
