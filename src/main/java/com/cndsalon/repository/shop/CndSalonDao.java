package com.cndsalon.repository.shop;

import java.util.List;

import com.cndsalon.domain.shop.CndSalonReviewVO;
import com.cndsalon.domain.shop.CndSalonShopDesignerVO;
import com.cndsalon.domain.shop.CndSalonShopInfoVO;
import com.cndsalon.domain.shop.CndSalonShopProductPhotoVO;

public interface CndSalonDao {

	public List<CndSalonShopInfoVO> getAll(String userLocalX,String userLocalY);
	
	public CndSalonShopInfoVO getShopDetail(String sCode);
	
	public List<CndSalonShopProductPhotoVO> getShopProductPhoto(String sCode);
	
	public List<CndSalonShopDesignerVO> getShopDesignerInfo(String sCode);
	
	public List<CndSalonReviewVO> getReview(String sCode);
	
	public List<CndSalonShopInfoVO> searchShop(
			CndSalonShopInfoVO ShopInfoVO);
	
}
