package com.cndsalon.domain.book;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.cndsalon.domain.shop.CndSalonShopInfoVO;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * 개요: booking 파트에서 테스트를 위한 Designer 엔티티 클래스
 * </pre>
 * @author <a href="mailto:hkj5455@gamil.com">김진혁</a><br>
 * @date 2020. 12. 12. 
 * @version 1.0
 * @since 
 * 디자이너 사진 정보 Embedded타입 혹은 OneToMany로 추가해야함.
 */
@Entity(name = "SHOP_DESIGNER_TBL")
@NoArgsConstructor
@Getter
public class Designer {
	
	@Id
	private String dCode; // 디자이너 코드
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = com.cndsalon.domain.shop.CndSalonShopInfoVO.class)
	@JoinColumn(name = "S_CODE")
	private CndSalonShopInfoVO shopInfo; // 매장
	
	private String dName; // 디자이너 이름
	private String dInfo; // 디자이너 정보(소개글)
	private Integer dReviewCount;  // 디자이너 리뷰 카운트(리뷰글의 해당 디자이너 Total) DEFAULT 0
	private Integer dAvgScore; // 리뷰글의 별점의 평균 DEFAULT 0
	private String dDayOff; // 디자이너 휴무일
}
