package com.cndsalon.domain.shop;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cndsalon.domain.book.Menu;

import lombok.Data;
import lombok.RequiredArgsConstructor;


/**
 * 
 * <pre>
 * ShopInfoVO : 매장 정보 VO
 * </pre>
 * @author <a href="simhyung777@naver.com">심현종</a></br>
 * @date ${date}
 * @version 1.0
 * @since 2020-12-07
 * @param 
 * userLocalX[String] : 접속 사용자 위도
 * userLocalY[String] : 접속 사용자 경도
 * sCode[String] : 매장 코드
 * sName[String] : 매장 이름
 * sAddr[String] : 매장 주소
 * sPhone[String] : 매장 전화번호
 * sGpsX[String] : 매장 위도
 * sGpsY[String] : 매장 경도
 * sTime[String] : 매장 운영시간
 * sTitle[String] : 매장 소개 제목
 * sContent[String] : 매장 소개 내용
 * sParking[boolean] : 주차가능 여부
 * sWifi[boolean] : wifi 가능 여부
 * sSubway[boolean] : 지하철 역근처
 * sCharge[boolean] : 추가요금 없음
 * sPickup[boolean] : 픽업여부
 * sBigdog[boolean] : 대형견 가능여부
 * sAvgScore[boolean] : 매장 평점
 * sLocale[boolean] : 거리 연산 결과
 * sPhotopath[String] : 매장 사진 경로
 * sPhotoname[String] : 매장 사진 이름
 * sPhotonameOrigin[String] : 매장 사진 원래 이름
 */
@Entity
@Table(name = "SHOP_INFO_TBL")
@Data
@RequiredArgsConstructor
public class CndSalonShopInfoVO {
	private String userLocalX;
	private String userLocalY;
	@Id
	@Column(name = "S_CODE")
	private String sCode;
	private String sName;
	private String sAddr;
	private String sPhone;
	private String sGpsX;
	private String sGpsY;
	private String sTime;
	private String sTitle;
	private String sContent;
	private boolean sParking;
	private boolean sWifi;
	private boolean sSubway;
	private boolean sCharge;
	private boolean sPickup;
	private boolean sBigdog;
	private double sAvgScore;
	private double sLocale;
	private String sPhotopath;
	private String sPhotoname;
	private String sPhotonameOrigin;
	
	@OneToMany(mappedBy = "shopInfo", targetEntity = com.cndsalon.domain.book.Menu.class)
	private List<Menu> menus = new ArrayList<>();
	
}// Class END
