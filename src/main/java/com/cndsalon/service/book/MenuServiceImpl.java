package com.cndsalon.service.book;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cndsalon.domain.book.Menu;
import com.cndsalon.repository.book.MenuDSLRepositorySupport;
import com.cndsalon.repository.book.MenuRepository;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private MenuDSLRepositorySupport MenuDSLRepositorySupport;
	
	@Override
	public List<Menu> getDogMenu(String sCode) {
		return this.MenuDSLRepositorySupport.findBySCode(sCode);
	}
	

}
