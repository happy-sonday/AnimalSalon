package com.cndsalon.repository.book;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cndsalon.domain.book.BookingView;
import com.cndsalon.domain.book.MenuOption;

/**
 * <pre>
 * 개요: JPA 복합키 이슈로 인한 Mybatis 혼용
 * </pre>
 * @author <a href="mailto:hkj5455@gmail.com">김진혁</a><br> 
 */
@Repository
public class BookingDaoImpl implements BookingDao {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<MenuOption> getMenuOptionList(String sCode, String mCode) {
		return sqlSession.getMapper(BookingMapper.class).getMenuOptionList(sCode, mCode);
	}

	@Override
	public BookingView getBookingView(String sCode, String mCode, String dCode) {
		return sqlSession.getMapper(BookingMapper.class).getBookingView(sCode, mCode, dCode);
	}
	
	
}
