package com.cndsalon.service.shop;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cndsalon.domain.shop.CndSalonReviewVO;
import com.cndsalon.domain.shop.CndSalonShopDesignerVO;
import com.cndsalon.domain.shop.CndSalonShopInfoVO;
import com.cndsalon.domain.shop.CndSalonShopProductPhotoVO;
import com.cndsalon.repository.shop.CndSalonDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ShopServiceImpl implements ShopListService {

	@Autowired
	private CndSalonDao dao;
	
	@Transactional
	@Override
	public List<CndSalonShopInfoVO> getAll(String userLocalX,String userLocalY,int pageNum){

		return dao.getAll(userLocalX,userLocalY,pageNum);
	}
	
	@Transactional
	@Override
	public CndSalonShopInfoVO getShopDetail(String sCode){

		return dao.getShopDetail(sCode);
	}

	@Transactional
	@Override
	public List<CndSalonShopProductPhotoVO> getShopProductPhoto(String sCode){

		return dao.getShopProductPhoto(sCode);
	}
	
	@Transactional
	@Override
	public List<CndSalonShopDesignerVO> getShopDesignerInfo(String sCode){

		return dao.getShopDesignerInfo(sCode);
	}


	@Transactional
	@Override
	public List<CndSalonReviewVO> getReview(String sCode){

		return dao.getReview(sCode);
	}

	@Transactional
	@Override
	public List<CndSalonShopInfoVO> searchShop
	(CndSalonShopInfoVO ShopInfoVO) {
		return dao.searchShop(ShopInfoVO);
	}

	@Override
	public int getPageNum() {

		return dao.getPageNum();
	}

	@Override
	public int getFilterPageNum(CndSalonShopInfoVO ShopInfoVO) {

		return dao.getFilterPageNum(ShopInfoVO);
	}
	

	
}
