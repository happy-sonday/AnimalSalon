package com.cndsalon.domain.book;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class MenuId implements Serializable {
	
    @EqualsAndHashCode.Include
    @Id
    private String mCode;
    
    @EqualsAndHashCode.Include
    @Id
    private String sCode;
    
    public MenuId(String mCode, String sCode) {
    	this.mCode = mCode;
    	this.sCode = sCode;
    }
}
