package com.cndsalon.repository.shop;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cndsalon.domain.shop.CndSalonShopInfoVO;


@Repository
public class CndSalonDaoImpl implements CndSalonDao {

	@Autowired
	private SqlSession sqlSession;
	

	@Override
	public List<CndSalonShopInfoVO> getAll(String userLocalX,String userLocalY) {
		
		return sqlSession.getMapper(CndSalonMapper.class).getAll(userLocalX,userLocalY);
	}

	@Override
	public CndSalonShopInfoVO getOne(String sCode) {
		
		return sqlSession.getMapper(CndSalonMapper.class).getOne(sCode);
	}
	
	public CndSalonDaoImpl() {
		
	}// 기본 생성자 END

	
}// Class END
