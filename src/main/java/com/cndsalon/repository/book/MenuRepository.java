package com.cndsalon.repository.book;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cndsalon.domain.book.Menu;

/**
 * <pre>
 * 개요: Spring-boot-jpa 기본 쿼리
 * </pre>
 * @author <a href="mailto:hkj5455@gmail.com">김진혁</a><br>
 */
public interface MenuRepository extends JpaRepository<Menu, String>{
	
	Menu findBymCode(String mCode);

}
