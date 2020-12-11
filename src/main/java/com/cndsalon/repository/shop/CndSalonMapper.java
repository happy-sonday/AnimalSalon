package com.cndsalon.repository.shop;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.cndsalon.domain.shop.CndSalonReviewVO;
import com.cndsalon.domain.shop.CndSalonShopDesignerVO;
import com.cndsalon.domain.shop.CndSalonShopInfoVO;
import com.cndsalon.domain.shop.CndSalonShopProductPhotoVO;

public interface CndSalonMapper {

	List<CndSalonShopInfoVO> getAll(@Param("userLocalX") String userLocalX,
			@Param("userLocalY") String userLocalY);
	
	CndSalonShopInfoVO getShopDetail(@Param("sCode") String sCode);

	List<CndSalonShopProductPhotoVO> getShopProductPhoto(@Param("sCode") String sCode);
	
	List<CndSalonShopDesignerVO> getShopDesignerInfo(@Param("sCode") String sCode);
	
	List<CndSalonReviewVO> getReview(@Param("sCode") String sCode);
	
	List<CndSalonShopInfoVO> searchShop(
			CndSalonShopInfoVO ShopInfoVO);
	

}
