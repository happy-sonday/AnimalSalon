package com.cndsalon.repository.book;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.cndsalon.domain.book.Menu;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.cndsalon.domain.book.QMenu.*;
import static com.cndsalon.domain.shop.QCndSalonShopInfoVO.*;


@Repository
public class MenuDSLRepositorySupport extends QuerydslRepositorySupport{
	private final JPAQueryFactory queryFactory;
	
	public MenuDSLRepositorySupport(JPAQueryFactory queryFactory) { // Bean으로 등록된 queryFactory를 생성자 인젝션 주입.
		super(Menu.class);
		this.queryFactory = queryFactory;
	}
	
	public List<Menu> findBySCode(String sCode){
		return queryFactory.selectFrom(menu)
				.innerJoin(menu.shopInfo, cndSalonShopInfoVO)
				.where(cndSalonShopInfoVO.sCode.eq(sCode))
				.fetch();
	}
	
	

}
