package com.cndsalon.repository.book;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.cndsalon.domain.book.MenuOption;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.cndsalon.domain.book.QMenu.*;
import static com.cndsalon.domain.book.QMenuOption.*;
import static com.cndsalon.domain.shop.QCndSalonShopInfoVO.*;

@Repository
public class MenuOptionDslRepository extends QuerydslRepositorySupport{
	private final JPAQueryFactory queryFactory;
	
	public MenuOptionDslRepository(JPAQueryFactory queryFactory) { // Bean으로 등록된 queryFactory를 생성자 인젝션 주입.
		super(MenuOption.class);
		this.queryFactory = queryFactory;
	}
	
	public List<MenuOption> findMenuOptionByMCode(String sCode, String mCode, String mType){
		List<MenuOption> mOptions = queryFactory.select(menuOption)
				.from(menuOption)
				.join(menuOption.menu, menu)
				.join(menuOption.shopInfo, cndSalonShopInfoVO)
				.where(cndSalonShopInfoVO.sCode.eq(sCode), menu.mCode.eq(mCode), menu.mType.eq(mType))
				.fetch();
		
		return mOptions;
	}
	
	
	

}