package com.cndsalon.domain.book;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.cndsalon.domain.shop.CndSalonShopInfoVO;

import lombok.Getter;
import lombok.NoArgsConstructor;

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
@Entity(name = "SHOP_MENU")
@Getter
@IdClass(MenuId.class)
@NoArgsConstructor
public class Menu {
	
	@Id
    private String mCode;    // 메뉴코드
	
	@Id
	private String sCode;	// 매장코드
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = com.cndsalon.domain.shop.CndSalonShopInfoVO.class)
	@JoinColumn(name = "sCode", updatable = false, insertable = false)
    private CndSalonShopInfoVO shopInfo;    // 매장테이블
	
    private String mType;    // 종분류 / 강아지 OR 고양이
	
    private String mName;    // 메뉴명
	
    private Integer mTime;   // 소요시간
	
    private Integer mPrice;  // 금액
	
    private String mInfo;    // 상품부가정보
	
    @Column(name = "M_P_PATH")
	private String mpPath;    // 사진파일경로
    
    @Column(name = "M_P_SYSNAME")
	private String mpSysName; // 사진파일시스템이름
    
    @Column(name = "M_P_ORGNAME")
	private String mpOrgName; // 사진파일원본이름
    
    @OneToMany(mappedBy = "menu")
	private List<MenuOption> menuOptions = new ArrayList<>();
	
//	@OneToMany(mappedBy = "menu")
//	private List<MenuPhoto> menuPhotos = new ArrayList<>();




	
}
