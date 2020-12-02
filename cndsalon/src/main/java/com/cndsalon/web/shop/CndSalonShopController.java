package com.cndsalon.web.shop;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cndsalon.service.shop.ShopListService;

import lombok.extern.java.Log;

@Controller
@Log
public class CndSalonShopController {

	@Autowired
	private ShopListService service;
	@RequestMapping("/")
	public String home() {
		log.info("Main Start");
		return "index";
	}
	@RequestMapping("/search")
	public String searchShop() {
		log.info("Main Start");
		return "/shop/test.html";
	}
	
	@RequestMapping("/getAll")
	public String getAll(Model model) {
		log.info("---------getAll Start--------------------");
		model.addAttribute("list", service.getAll());
		return "/shop/test2.html";
	}
	
	@RequestMapping(value="/getOne")
	public String getOne(@RequestParam("sCode") String sCode, Model model) {
		log.info("--------getOne Start---------"+sCode);
		model.addAttribute("shop",service.getOne(sCode));
		
		return "/shop/test4_shop_detail.html";
	}
	
	@RequestMapping("/getLocation")
	public String getAll() {
		log.info("---------getLocation Start--------------------");
		InetAddress local;
		try {
			// 중복방지
			local=InetAddress.getLocalHost();
			String ip = local.getHostAddress();
			log.info("------ client IP "+ip+"-----");
			
		}catch(UnknownHostException e) {
			e.printStackTrace();
		}
		return "/shop/test_location.html";
	}

	@RequestMapping("/getAll_ajax")
	public String getAll_ajax(Model model) {
		log.info("---------getAll_ajax Start--------------------");
		model.addAttribute("list", service.getAll());
		return "/shop/test3_ajax.html";
	}
	

	
	public CndSalonShopController() {
		// TODO Auto-generated constructor stub
	}// 기본 생성자 END

}//Class END
