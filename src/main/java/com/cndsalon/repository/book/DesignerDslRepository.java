package com.cndsalon.repository.book;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.cndsalon.domain.book.Designer;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.cndsalon.domain.book.QDesigner.*;
import static com.cndsalon.domain.shop.QCndSalonShopInfoVO.*;


@Repository
public class DesignerDslRepository extends QuerydslRepositorySupport{
	private final JPAQueryFactory queryFactory;
	
	public DesignerDslRepository(JPAQueryFactory queryFactory) { // Bean으로 등록된 queryFactory를 생성자 인젝션 주입.
		super(Designer.class);
		this.queryFactory = queryFactory;
	}
	
	public List<Designer> findBySCode(String sCode){
		List<Designer> designers = queryFactory.selectFrom(designer)
				.join(designer.shopInfo, cndSalonShopInfoVO)
				.where(cndSalonShopInfoVO.sCode.eq(sCode))
				.fetch();
		
		System.out.println("테스트 로그 : 디자이너 인원 수 : " + designers.size());
		return designers;
	}
	
	
	
	

}