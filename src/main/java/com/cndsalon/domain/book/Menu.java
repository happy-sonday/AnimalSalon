package com.cndsalon.domain.book;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cndsalon.domain.shop.CndSalonShopInfoVO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 개요: 샵의 메뉴 Entity
 * </pre>
 * @author <a href="mailto:hkj5455@gmail.com">김진혁</a><br>
 * @date 2020. 12. 8. 
 * @version 1.0
 * @since
 * 사업자가 직접 메뉴를 등록시킬 수 있다는 점(확장성) 고려했기 때문에 
 * 테스트 데이터 삽입 시, 업체 마다 메뉴를 추가해야한다.
 */
@Entity
@Table(name = "SHOP_MENU")
@Getter
@NoArgsConstructor
public class Menu {
	@Id
	@Column(name = "M_CODE")
    private String mCode;    // 메뉴코드
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = com.cndsalon.domain.shop.CndSalonShopInfoVO.class)
	@JoinColumn(name = "S_CODE")
    private CndSalonShopInfoVO shopInfo;    // 매장테이블
	
	@Column(name = "M_TYPE")
    private String mType;    // 종분류 / 강아지 OR 고양이
	
	@Column(name = "M_NAME")
    private String mName;    // 메뉴명
	
	@Column(name = "M_TIME")
    private Integer mTime;   // 소요시간
	
	@Column(name = "M_PRICE")
    private Integer mPrice;  // 금액
	
	@Column(name = "M_INFO")
    private String mInfo;    // 상품부가정보
	
	@ElementCollection
	@CollectionTable(name = "MenuOption", joinColumns = @JoinColumn(name="M_CODE"))
	private List<MenuOption> menuOptions;
	
	@OneToMany(mappedBy = "menu")
	private List<MenuPhoto> menuPhotos;
	
	 // 테스트 생성자
	public Menu(String mCode, String mType, String mName, Integer mTime, Integer mPrice, String mInfo, CndSalonShopInfoVO shopInfo) {
		this.mCode = mCode;
		this.mType = mType;
		this.mName = mName;
		this.mTime = mTime;
		this.mPrice = mPrice;
		this.mInfo = mInfo;
		this.shopInfo = shopInfo;
	}

}
