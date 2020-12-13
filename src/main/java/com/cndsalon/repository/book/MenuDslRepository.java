package com.cndsalon.repository.book;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cndsalon.domain.book.Menu;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.cndsalon.domain.book.QMenu.*;
import static com.cndsalon.domain.shop.QCndSalonShopInfoVO.*;

@Repository
public class MenuDslRepository {
	private final JPAQueryFactory queryFactory;
	
	public MenuDslRepository(JPAQueryFactory queryFactory) { // Bean으로 등록된 queryFactory를 생성자 인젝션 주입.
		this.queryFactory = queryFactory;
	}
	
	public List<Menu> findBySCode(String sCode, String mType){
		
		return queryFactory.selectFrom(menu)
				.innerJoin(menu.shopInfo, cndSalonShopInfoVO)
				.where(cndSalonShopInfoVO.sCode.eq(sCode), menu.mType.eq(mType))
				.fetch();
	}
	

}