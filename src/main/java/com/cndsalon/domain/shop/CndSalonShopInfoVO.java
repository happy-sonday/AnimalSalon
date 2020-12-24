package com.cndsalon.domain.shop;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cndsalon.domain.book.Menu;
import com.cndsalon.domain.book.MenuOption;

import lombok.Data;
import lombok.NoArgsConstructor;


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
 * pageNum[int] : 현재 page번호
 * pageMax[int] : 현재 검색 조건의 최대 page
 * userLocalX[String] : 사용자 X좌표
 * userLocalY[String] : 사용자 Y좌표
 * sCode[String] : 매장 코드
 * sName[String] : 매장 이름
 * sAddr[String] : 매장 주소
 * sPhone[String] : 매장 전화번호
 * sGpsX[String] : 매장 X좌표
 * sGpsY[String] : 매장 Y좌표
 * sTime[String] : 매장 운영시간 
 * sTitle[String] : 매장 소개 제목
 * sContent[String] : 매장 소개 내용
 * sParking[boolean] : 매장 주차 가능 여부
 * sWifi[boolean] : 매장 wifi 가능
 * sSubway[boolean] : 매장 지하철 역주변
 * sCharge[boolean] : 매장 추가요금 없음
 * sPickup[boolean] : 픽업 가능 여부
 * sBigdog[boolean] : 대형견 가능 여부
 * sAvgScore[double] : 매장 평균 평점
 * sLocale[double] : 사용자 위치 대비 매장 거리
 * sPhotopath[String] : 매장 사진 경로
 * sPhotoname[String] : 매장 사진 파일 이름
 * sPhotonameOrigin[String] : 매장 사진 파일 원래 이름
 * pCat[boolean] : 고양이 검색 옵션
 * pDog[boolean] : 강아지 검색 옵션
 * pShort[boolean] : 고양이 단모 검색 옵션
 * pLong[boolean] : 고양이 장모 검색 옵션
 * pSmall[boolean] : 강아지 소형견 검색 옵션
 * pMedium[boolean] : 강아지 중현견 검색 옵션
 * pLarge[boolean] : 강아지 대형견 검색 옵션
 */
@Entity
@Table(name = "SHOP_INFO_TBL")
@Data
@NoArgsConstructor
@IdClass(ShopInfoId.class)
public class CndSalonShopInfoVO {
	private int pageNum;
	private int pageMax;
	private String userLocalX;
	private String userLocalY;
	@Id
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
	@Transient
	private double sLocale;
	private String sPhotopath;
	private String sPhotoname;
	private String sPhotonameOrigin;
	private boolean pCat;
	private boolean pDog;
	private boolean pShort;
	private boolean pLong;
	private boolean pSmall;
	private boolean pMedium;
	private boolean pLarge;
	
	@OneToMany(mappedBy = "shopInfo", targetEntity = com.cndsalon.domain.book.Menu.class)
	private List<Menu> menus = new ArrayList<>();
	
}// Class END
