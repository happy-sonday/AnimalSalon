package com.cndsalon.repository.shop;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cndsalon.domain.shop.CndSalonShopInfoVO;

@Mapper
public interface CndSalonMapper {

	//@Select("SELECT * FROM shop_info_tbl ORDER BY s_code")
	
	
	@Select("SELECT C.* FROM\r\n"
			+ "(SELECT DENSE_RANK() OVER (PARTITION BY A.S_CODE ORDER BY B.S_PHOTONAME) AS NO,\r\n"
			+ "round((( ACOS(SIN(37.5605672 * 3.14159265359 / 180) * SIN(S_GPS_X * 3.14159265359 / 180) +\r\n"
			+ "COS(37.5605672 * 3.14159265359 / 180) * COS(S_GPS_X * 3.14159265359 / 180) * COS((126.94334859999998 - S_GPS_Y)\r\n"
			+ "* 3.14159265359 / 180)) * 180 / 3.14159265359) * 60 * 1.1515),3) AS S_LOCALE,\r\n"
			+ "A.S_CODE,A.S_NAME,A.S_ADDR,A.S_TIME,A.S_TITLE,A.S_AVG_SCORE,A.S_GPS_X,A.S_GPS_Y,A.S_PARKING,\r\n"
			+ "B.S_PHOTOPATH, B.S_PHOTONAME, B.S_PHOTONAME_ORIGIN \r\n"
			+ "FROM SHOP_INFO_TBL A, SHOP_PHOTO_TBL B\r\n"
			+ "WHERE A.S_CODE = B.S_CODE(+)\r\n"
			+ ")C WHERE NO <2 ORDER BY S_LOCALE")
	List<CndSalonShopInfoVO> getAll();
	
	
	@Select("SELECT * FROM shop_info_tbl WHERE s_code = #{sCode}")
	CndSalonShopInfoVO getOne(@Param("sCode") String sCode);
	
	
}
