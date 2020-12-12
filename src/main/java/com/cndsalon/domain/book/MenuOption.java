package com.cndsalon.domain.book;

import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import com.cndsalon.domain.shop.CndSalonShopInfoVO;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * 개요: 업체의 각 메뉴 중 옵션 Entity
 * </pre>
 * @author <a href="mailto:hkj5455@gmail.com">김진혁</a><br>
 * @date 2020. 12. 12. 
 * @version 1.1
 * @since 2020. 12. 8.
 * 임베디드타입으로 설정해놓고 작업했음. 진행하면서 크게 문제될 것 같아보이진 않으나
 * 임베디드 사용에 어려움이 있을 경우 옵션코드(PK)를 넣어서 다대일 방식으로 사용할 예정 
 * 임베디드타입에서 -> IdClass로 변경
 */
@Getter
@Entity(name = "SHOP_MENU_OPTION")
@NoArgsConstructor
@IdClass(MenuId.class)
public class MenuOption {

	@Id
	private String mCode; // 메뉴코드
	
	@Id
	private String sCode; // 매장코드
	
	private String oName; // 옵션명
    
	private Integer oTime;  // 추가 소요시간
    
	private Integer oPrice; // 추가 금액
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns(value = {
		@JoinColumn(name = "sCode", updatable = false, insertable = false),
		@JoinColumn(name = "mCode", updatable = false, insertable = false)
	})
	private Menu menu;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns(value = {
			@JoinColumn(name = "sCode", updatable = false, insertable = false)	
		})
	private CndSalonShopInfoVO shopInfo;
	
	public void setMenu(Menu menu) {
		if(menu != null) {
			menu.getMenuOptions().remove(this);
		}
		this.menu = menu;
		this.menu.getMenuOptions().add(this);
	}
	
	public void setShopInfo(CndSalonShopInfoVO shopInfo) {
		if(shopInfo != null) {
			shopInfo.getMenuOptions().remove(this);
		}
		this.shopInfo = shopInfo;
		this.shopInfo.getMenuOptions().add(this);
	}
    

}
