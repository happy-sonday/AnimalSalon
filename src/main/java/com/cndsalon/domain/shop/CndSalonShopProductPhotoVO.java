package com.cndsalon.domain.shop;


import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * CndSalonShopProductPhotoVO : 매장 사진 정보 VO
 * </pre>
 * @author <a href="simhyung777@naver.com">심현종</a></br>
 * @date ${date}
 * @version 1.0
 * @since 2020-12-21
 * @param
 * sCode[String] : 매장 코드
 * pPhotopath[String] : 매장 상품 사진 경로
 * pPhotoname[String] : 매장 상품 사진 이름
 * pPhotonameOrigin[String] : 매장 상품 사진 원래 이름
 */
@Data
@NoArgsConstructor
public class CndSalonShopProductPhotoVO {
	private String sCode;
	private String pPhotopath;
	private String pPhotoname;
	private String pPhotonameOrigin;
		
}
