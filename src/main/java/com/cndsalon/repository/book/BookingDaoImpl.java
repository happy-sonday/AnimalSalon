package com.cndsalon.repository.book;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cndsalon.domain.book.MenuOption;

@Repository
public class BookingDaoImpl implements BookingDao {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<MenuOption> getMenuOptionList(String sCode, String mCode) {
		return sqlSession.getMapper(BookingMapper.class).getMenuOptionList(sCode, mCode);
	}
	
	
}
