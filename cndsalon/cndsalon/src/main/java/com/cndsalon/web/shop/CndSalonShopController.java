package com.cndsalon.web.shop;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cndsalon.domain.shop.CndSalonShopInfoVO;
import com.cndsalon.service.shop.ShopListService;


import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
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
		log.info("---- Main Start ----");

		return "/shop/test.html";
	}

	@RequestMapping(value = "/getAll")
	public String getAll(@RequestParam("userLocalX") String userLocalX, @RequestParam("userLocalY") String userLocalY,
			Model model) {
		log.info("---좌표 계산 ---   " + userLocalX + "  &&&&&  " + userLocalY + "   ");
		log.info("---------getAll Start--------------------");

		if (!userLocalX.equals("") && !userLocalY.equals("")) {
			log.info("---입력 좌표확인---" + userLocalX + "++++" + userLocalY);
			model.addAttribute("list", service.getAll(userLocalX, userLocalY));

		} else {
			userLocalX += "37.62843";
			userLocalY += "127.07184";
			log.info("---기본 좌표확인---   " + userLocalX + "  &&&&&  " + userLocalY + "   ");
			model.addAttribute("list", service.getAll(userLocalX, userLocalY));
		}

		return "/shop/test2.html";
	}

	@RequestMapping(value = "/getAll_ajax")
	public String getAll_ajax(Model model) {
		
		log.info("---------getAll_ajax Start--------------------");
		
		return "/shop/test3_getall_ajax.html";
	}
	
	@ResponseBody
	@RequestMapping(value = "/getAll_ajax_list", method = { RequestMethod.GET },
			produces="application/json; charset=UTF-8")
	public ResponseEntity<List<CndSalonShopInfoVO>> getAll_ajax_list(@RequestParam("userLocalX") String userLocalX,
			@RequestParam("userLocalY") String userLocalY) {
		List<CndSalonShopInfoVO> list = null;
		log.info("---------getAll_ajax_list Start--------------------");
		if (!userLocalX.equals("") || !userLocalY.equals("")) {
			log.info("---입력 좌표확인---" + userLocalX + "++++" + userLocalY);
			list= service.getAll(userLocalX, userLocalY);

		} else {
			userLocalX += "37.62843";
			userLocalY += "127.07184";
			log.info("---기본 좌표확인---   " + userLocalX + "  &&&&&  " + userLocalY + "   ");
			list= service.getAll(userLocalX, userLocalY);
		}
		return new ResponseEntity<List<CndSalonShopInfoVO>>(list,HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/getOne")
	public String getOne(@RequestParam("sCode") String sCode, Model model) {
		log.info("--------getOne Start---------" + sCode);
		model.addAttribute("shop", service.getOne(sCode));

		return "/shop/test4_shop_detail.html";
	}

	@RequestMapping("/getLocation")
	public String getAll() {
		log.info("---------getLocation Start--------------------");
		InetAddress local;
		try {
			// 중복방지
			local = InetAddress.getLocalHost();
			String ip = local.getHostAddress();
			log.info("------ client IP " + ip + "-----");

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return "/shop/test_location.html";
	}

	public CndSalonShopController() {
		// TODO Auto-generated constructor stub
	}// 기본 생성자 END

}// Class END
