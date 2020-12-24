package com.cndsalon.domain.shop;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * <pre>
 * CndSalonShopDesignerVO : 디자이너 상세정보 VO
 * </pre>
 * @author <a href="simhyung777@naver.com">심현종</a></br>
 * @date ${date}
 * @version 1.0
 * @since 2020-12-21
 * @param 
 * sCode[String] : 매장 코드
 * dCode[String] : 디자이너 코드
 * dName[String] : 디자이너 이름
 * dInfo[String] : 디자이너 정보(디자이너 소개)
 * dReviewCount[String] : 디자이너 리뷰 갯수
 * dAvgScore[double] : 디자이너 리뷰의 평점 평균
 * dDayOff[String] : 디자이너 휴무일
 * dPhotopath[String] : 디자이너 사진 파일 경로
 * dPhotoname[String] : 디자이너 사진 파일 이름
 * dPhotoOrigin[String] : 디자이너 사진 파일 원래 이름
 */
@Data
@NoArgsConstructor
public class CndSalonShopDesignerVO {
	private String sCode;
	private String dCode;
	private String dName;
	private String dInfo;
	private String dReviewCount;
	private double dAvgScore;
	private String dDayOff;
	private String dPhotopath;
	private String dPhotoname;
	private String dPhotonameOrigin;
}
