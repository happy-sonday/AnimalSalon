package com.cndsalon.repository.book;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cndsalon.domain.book.Designer;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.cndsalon.domain.book.QDesigner.*;
import static com.cndsalon.domain.shop.QCndSalonShopInfoVO.*;


@Repository
public class DesignerDslRepository {
	private final JPAQueryFactory queryFactory;
	
	public DesignerDslRepository(JPAQueryFactory queryFactory) { // Bean으로 등록된 queryFactory를 생성자 인젝션 주입.
		this.queryFactory = queryFactory;
	}
	
	public List<Designer> findDesignerBySCode(String sCode){
		return queryFactory.select(designer)
				.from(designer)
				.join(designer.shopInfo, cndSalonShopInfoVO)
				.where(cndSalonShopInfoVO.sCode.eq(sCode))
				.orderBy(designer.dCode.asc())
				.fetch();
	}
	
	
	
	

}