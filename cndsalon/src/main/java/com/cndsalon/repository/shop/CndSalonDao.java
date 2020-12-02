package com.cndsalon.repository.shop;

import java.util.List;

import com.cndsalon.domain.shop.CndSalonShopInfoVO;

public interface CndSalonDao {

	public List<CndSalonShopInfoVO> getAll();
	
	public CndSalonShopInfoVO getOne(String sCode);
	
	
}
