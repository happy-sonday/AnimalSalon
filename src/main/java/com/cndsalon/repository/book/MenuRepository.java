package com.cndsalon.repository.book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cndsalon.domain.book.Menu;

public interface MenuRepository extends JpaRepository<Menu, String>{

	@Query(value = "select M.M_CODE, M.M_TYPE, M.M_NAME, M.M_TIME, M.M_PRICE, M.M_INFO "
			+ "from SHOP_MENU M "
			+ "where M.S_CODE=:sCode"
			, nativeQuery = true)
	List<Menu> findByShopInfo(@Param("sCode") String sCode);
	

}
