package com.cndsalon.service.shop;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cndsalon.domain.shop.CndSalonShopInfoVO;
import com.cndsalon.repository.shop.CndSalonDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ShopServiceImpl implements ShopListService {

	@Autowired
	private CndSalonDao dao;
	
	@Transactional
	@Override
	public List<CndSalonShopInfoVO> getAll(String userLocalX,String userLocalY){

		return dao.getAll(userLocalX,userLocalY);
	}
	
	@Transactional
	@Override
	public CndSalonShopInfoVO getOne(String sCode){

		return dao.getOne(sCode);
	}
}
