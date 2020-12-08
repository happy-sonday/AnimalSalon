package com.cndsalon.repository.shop;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cndsalon.domain.shop.CndSalonShopInfoVO;

public interface CndSalonMapper {

	//@Select("SELECT * FROM shop_info_tbl ORDER BY s_code")
	
	
	
	List<CndSalonShopInfoVO> getAll(@Param("userLocalX") String userLocalX,
			@Param("userLocalY") String userLocalY);
	
	
	
	CndSalonShopInfoVO getOne(@Param("sCode") String sCode);
	
	/* 총 page 갯수 소숫점 자리 버림 처리
	@Select("SELECT TRUNC(COUNT(*)/10) FROM\r\n"
			+ "(SELECT DENSE_RANK() OVER (PARTITION BY A.S_CODE ORDER BY B.S_PHOTONAME) AS NO
			 FROM SHOP_INFO_TBL A, SHOP_PHOTO_TBL B WHERE A.S_CODE = B.S_CODE(+))C\r\n"
			+ "WHERE NO <2 ")
	*/
}
