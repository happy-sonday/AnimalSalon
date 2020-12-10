package com.cndsalon.repository.shop;

import java.util.List;

import com.cndsalon.domain.shop.CndSalonShopInfoVO;

public interface CndSalonDao {

	public List<CndSalonShopInfoVO> getAll(String userLocalX,String userLocalY);
	
	public CndSalonShopInfoVO getOne(String sCode);
	
	public List<CndSalonShopInfoVO> searchShop(String sParking, String sWifi, String sSubway, String sCharge,
			String sPickup, String sBigdog,String userLocalX,String userLocalY);
	
}
