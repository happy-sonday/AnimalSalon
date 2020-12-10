package com.cndsalon.repository.shop;

import java.util.List;

import com.cndsalon.domain.shop.CndSalonShopInfoVO;

public interface CndSalonDao {

	public List<CndSalonShopInfoVO> getAll(String userLocalX,String userLocalY);
	
	public CndSalonShopInfoVO getOne(String sCode);
	
	public List<CndSalonShopInfoVO> searchShop(
			CndSalonShopInfoVO ShopInfoVO);
	
}
