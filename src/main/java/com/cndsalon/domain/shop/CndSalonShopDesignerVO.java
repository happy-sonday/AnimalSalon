package com.cndsalon.domain.shop;

import lombok.Data;
import lombok.NoArgsConstructor;

// 디자이너 디자이너 정보 사진VO

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
