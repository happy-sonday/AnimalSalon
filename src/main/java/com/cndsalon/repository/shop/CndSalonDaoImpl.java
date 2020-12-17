package com.cndsalon.repository.shop;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cndsalon.domain.shop.CndSalonReviewVO;
import com.cndsalon.domain.shop.CndSalonShopDesignerVO;
import com.cndsalon.domain.shop.CndSalonShopInfoVO;
import com.cndsalon.domain.shop.CndSalonShopProductPhotoVO;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Repository
public class CndSalonDaoImpl implements CndSalonDao {

	@Autowired
	private SqlSession sqlSession;
	

	@Override
	public List<CndSalonShopInfoVO> getAll(String userLocalX,String userLocalY,int pageNum) {
		
		return sqlSession.getMapper(CndSalonMapper.class).getAll(userLocalX,userLocalY,pageNum);
	}

	@Override
	public List<CndSalonShopInfoVO> searchShop(
			CndSalonShopInfoVO ShopInfoVO) {
		
		return sqlSession.getMapper(CndSalonMapper.class).searchShop(
				ShopInfoVO);
	}
	
	@Override
	public CndSalonShopInfoVO getShopDetail(String sCode) {
		
		return sqlSession.getMapper(CndSalonMapper.class).getShopDetail(sCode);
	}

	@Override
	public List<CndSalonShopProductPhotoVO> getShopProductPhoto(String sCode) {
		
		return sqlSession.getMapper(CndSalonMapper.class).getShopProductPhoto(sCode);
	}
	
	@Override
	public List<CndSalonShopDesignerVO> getShopDesignerInfo(String sCode) {
		
		return sqlSession.getMapper(CndSalonMapper.class).getShopDesignerInfo(sCode);
	}
	
	@Override
	public List<CndSalonReviewVO> getReview(String sCode) {
		
		return sqlSession.getMapper(CndSalonMapper.class).getReview(sCode);
	}

	@Override
	public int getPageNum() {
		
		return sqlSession.getMapper(CndSalonMapper.class).getPageNum();
	}

	@Override
	public int getFilterPageNum(CndSalonShopInfoVO ShopInfoVO) {
		
		return sqlSession.getMapper(CndSalonMapper.class).getFilterPageNum(ShopInfoVO);
	}

	@Override
	public List<CndSalonReviewVO> getReviewPhoto(String rCode) {
		
		return sqlSession.getMapper(CndSalonMapper.class).getReviewPhoto(rCode);
	}
}// Class END
