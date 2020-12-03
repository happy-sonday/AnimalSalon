package com.cndsalon.domain.shop;

import lombok.Data;
import lombok.NoArgsConstructor;

// 매장정보 VO
@Data//(geter,seter)
@NoArgsConstructor// 기본생성자 자동주입(lombok)
public class CndSalonShopInfoVO {
	// 접속자의 위도
	private String userLocalX;
	// 접속자의 경도
	private String userLocalY;
	// schemas:CNDSALON tbl:SHOP_INFO_TBL
	// 매장 코드
	private String sCode;
	// 매장 이름
	private String sName;
	// 매장 주소
	private String sAddr;
	// 매장 전화번호
	private String sPhone;
	// 매장 x축좌표
	private String sGpsX;
	// 매장 y축좌표
	private String sGpsY;
	// 매장 운영 시간
	private String sTime;
	// 매장 소개글 제목
	private String sTitle;
	// 매장 소개글 내용
	private String sContent;
	// 매장 주차가능
	private boolean sParking;
	// 매장 wifi
	private boolean sWifi;
	// 지하철 근처
	private boolean sSubway;
	// 추가요금 여부
	private boolean sCharge;
	// 픽업여부
	private boolean sPickup;
	// 대형견 가능
	private boolean sBigdog;
	// 디자이너 평점 종합 평균
	private double sAvgScore;
	// 자기 위치 대비 거리
	private double sLocale;
	// 매장 사진 경로
	private String sPhotopath;
	// 매장 사진 파일이름
	private String sPhotoname;
	// 매장 사진 파일 원래 이름
	private String sPhotonameOrigin;
}// Class END
