package com.cndsalon.repository.shop;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cndsalon.domain.shop.CndSalonShopInfoVO;

@Mapper
public interface CndSalonMapper {

	//@Select("SELECT * FROM shop_info_tbl ORDER BY s_code")
	
	
	@Select("SELECT D.* FROM (SELECT ROW_NUMBER() OVER(ORDER BY S_LOCALE) AS RNUM,C.* FROM\r\n"
			+ "(SELECT DENSE_RANK() OVER (PARTITION BY A.S_CODE ORDER BY B.S_PHOTONAME) AS NO, \r\n"
			+ "round((( ACOS(SIN(${userLocalX} * 3.14159265359 / 180) * SIN(S_GPS_X * 3.14159265359 / 180) +\r\n"
			+ "COS(${userLocalX} * 3.14159265359 / 180) * COS(S_GPS_X * 3.14159265359 / 180) * COS((${userLocalY} - S_GPS_Y)\r\n"
			+ "* 3.14159265359 / 180)) * 180 / 3.14159265359) * 60 * 1.1515),3) AS S_LOCALE, A.*,B.S_PHOTOPATH, B.S_PHOTONAME, B.S_PHOTONAME_ORIGIN FROM SHOP_INFO_TBL A, SHOP_PHOTO_TBL B WHERE A.S_CODE = B.S_CODE(+))C\r\n"
			+ "WHERE NO <2 ORDER BY S_LOCALE)D WHERE RNUM BETWEEN (1-1)*10+1 AND 1*10")
	List<CndSalonShopInfoVO> getAll(@Param("userLocalX") String userLocalX, @Param("userLocalY") String userLocalY);
	
	
	@Select("SELECT * FROM shop_info_tbl WHERE s_code = #{sCode}")
	CndSalonShopInfoVO getOne(@Param("sCode") String sCode);
	
	/* 총 page 갯수 소숫점 자리 버림 처리
	@Select("SELECT TRUNC(COUNT(*)/10) FROM\r\n"
			+ "(SELECT DENSE_RANK() OVER (PARTITION BY A.S_CODE ORDER BY B.S_PHOTONAME) AS NO
			 FROM SHOP_INFO_TBL A, SHOP_PHOTO_TBL B WHERE A.S_CODE = B.S_CODE(+))C\r\n"
			+ "WHERE NO <2 ")
	*/
}
