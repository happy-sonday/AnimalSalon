package com.cndsalon.domain.book;

import javax.persistence.Column;
import javax.persistence.Embeddable;
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
 * 임베디드로 설정해놓고 작업했음. 진행하면서 크게 문제될 것 같아보이진 않으나
 * 임베디드 사용에 어려움이 있을 경우 옵션코드(PK)를 넣어서 다대일 방식으로 사용할 예정 
 */
@Embeddable
@Getter
@Table(name = "SHOP_MENU_OPTION")
public class MenuOption {
	
//	@Column(name = "M_CODE")
//    private String mCode; // 메뉴코드

//	@Column(name = "S_CODE")
//	private String sCode; // 매장코드
    
    @Column(name = "O_NAME")
	private String oCode; // 옵션명
    
    @Column(name = "O_TIME")
	private Integer oTime;  // 추가 소요시간
    
    @Column(name = "O_PRICE")
	private Integer oPrice; // 추가 금액
}
