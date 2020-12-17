package com.cndsalon.util.book;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;

import org.springframework.context.annotation.Bean;

import com.cndsalon.repository.book.BookingDslRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class TestLocalTime {

	public static void main(String[] args) {
		
////		 소요시간 제어 Test
//		LocalDateTime origin = LocalDateTime.now();
//		LocalDateTime plus = origin.plusMinutes(0);
//		System.out.println(plus);
		
//		TimeUtil test = new TimeUtil();
//		
//		String sTime = "09:00~18:00";
//		String getDate = "2020-12-15(화)";
//		test.createTimeList(sTime, getDate);
		

		

	}

}
