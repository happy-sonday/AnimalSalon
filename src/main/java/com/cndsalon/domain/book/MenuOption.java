package com.cndsalon.domain.book;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;

/**
 * <pre>
 * 개요: 업체의 각 메뉴 중 옵션 Entity
 * </pre>
 * @author <a href="mailto:hkj5455@gmail.com">김진혁</a><br>
 * @date 2020. 12. 8. 
 * @version 1.0
 * @since 
 */
@Entity
@Getter
@Table(name = "SHOP_MENU_OPTION")
public class MenuOption {
	
	@OneToMany
	@JoinColumn(name = "M_CODE")
    private String mCode; // 메뉴코드

	@OneToMany
    @JoinColumn(name = "S_CODE")
	private String sCode; // 매장코드
    
    @Column(name = "O_NAME")
	private String oCode; // 옵션명
    
    @Column(name = "O_TIME")
	private Integer oTime;  // 추가 소요시간
    
    @Column(name = "O_PRICE")
	private Integer oPrice; // 추가 금액
}
