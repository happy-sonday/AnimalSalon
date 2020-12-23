package com.cndsalon.domain.shop;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

// 리뷰글 VO
@Data
@NoArgsConstructor
public class CndSalonReviewVO {

	private String rCode;
	private String sCode;
	private String dCode;
	private String rWriter;
	private String rTitle;
	private String rContent;
	private double rScore;
	private int rEmpaty;
	private int rCount;
	private Date rDate;
	private String rWriterIp;
	private String rPhotopath;
	private String rPhotoname;
	private String rPhotonameOrigin;
	

}
