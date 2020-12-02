package com.cndsalon.service.shop;

import java.util.List;

import com.cndsalon.domain.shop.CndSalonShopInfoVO;

public interface ShopListService {

	public List<CndSalonShopInfoVO> getAll();
	
	public CndSalonShopInfoVO getOne(String sCode);
	
	
}
