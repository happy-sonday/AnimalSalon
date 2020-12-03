package com.cndsalon.repository.shop;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cndsalon.domain.shop.CndSalonShopInfoVO;

@Mapper
public interface CndSalonMapper {

	@Select("SELECT * FROM shop_info_tbl ORDER BY s_code")
	List<CndSalonShopInfoVO> getAll();
	
	
	@Select("SELECT * FROM shop_info_tbl WHERE s_code = #{sCode}")
	CndSalonShopInfoVO getOne(@Param("sCode") String sCode);
	
	
}
