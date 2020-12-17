package com.cndsalon.domain.shop;


import lombok.Data;
import lombok.NoArgsConstructor;

//상품 사진
@Data
@NoArgsConstructor
public class CndSalonShopProductPhotoVO {
	private String sCode;
	private String pPhotopath;
	private String pPhotoname;
	private String pPhotonameOrigin;
		
}
