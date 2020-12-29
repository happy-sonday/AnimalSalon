package com.cndsalon.util.book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.querydsl.jpa.impl.JPAQueryFactory;

/**
 * <pre>
 * 개요: JPA의 쿼리언어인 QueryDsl 설정 파일. JPAQueryFactory를 빈 객체로 생성 + EntityManager
 * </pre>
 * @author <a href="mailto:hkj5455@gmail.com">김진혁</a><br>
 */
@Configuration
public class QueryDSLConfig {
	
	@PersistenceContext
	private EntityManager em;
	
	@Bean
	public JPAQueryFactory queryFactory(EntityManager em) {
		return new JPAQueryFactory(em);
	}
}
