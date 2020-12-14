package com.cndsalon.domain.book;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;

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
public class Menu {
	@Id
	@Column(name = "M_CODE")
    private String mCode;    // 메뉴코드
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "S_CODE")
    private String sCode;    // 매장코드
	
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
	
	@OneToMany(mappedBy = "menu")
	private List<MenuOption> menuOptions = new ArrayList<>();
	
	@OneToMany(mappedBy = "menu")
	private List<MenuPhoto> menuPhotos = new ArrayList<>();
}
