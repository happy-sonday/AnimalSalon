package com.cndsalon.domain.book;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;

/**
 * <pre>
 * 개요: 업체의 각 메뉴 중 사진 Entity
 * </pre>
 * @author <a href="mailto:hkj5455@gmail.com">김진혁</a><br>
 * @date 2020. 12. 8. 
 * @version 1.0
 * @since 
 */
@Entity
@Getter
@Table(name = "SHOP_MENU_PHOTO")
public class MenuPhoto {	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "M_CODE")
	private Menu menu;
	
	@Column(name = "S_CODE")
	private String sCode;                // 매장코드
	
	@Column(name = "M_P_PATH")
	private String mpPath;    // 사진파일경로
	
	@Column(name = "M_P_SYSNAME")
	private String mpSysName; // 사진파일시스템이름
	
	@Id
	@Column(name = "M_P_ORGNAME")
	private String mpOrgName; // 사진파일원본이름
}
