package com.cndsalon.repository.shop;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import com.cndsalon.domain.shop.CndSalonShopInfoVO;

public interface CndSalonMapper {

	//@Select("SELECT * FROM shop_info_tbl ORDER BY s_code")
	
	
	
	List<CndSalonShopInfoVO> getAll(@Param("userLocalX") String userLocalX,
			@Param("userLocalY") String userLocalY);
	
	
	
	CndSalonShopInfoVO getOne(@Param("sCode") String sCode);



	List<CndSalonShopInfoVO> searchShop(
			@Param("sParking") String sParking,
			@Param("sWifi") String sWifi,
			@Param("sSubway") String sSubway,
			@Param("sCharge") String sCharge,
			@Param("sPickup") String sPickup,
			@Param("sBigdog") String sBigdog,
			@Param("userLocalX") String userLocalX,
			@Param("userLocalY") String userLocalY);
	

}
