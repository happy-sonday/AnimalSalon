package com.cndsalon.web.shop;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cndsalon.domain.shop.CndSalonReviewVO;
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
		return "index";
	}

	// Filter 검색
	@ResponseBody
	@RequestMapping(value = "/shopmain_search", method = { RequestMethod.GET },
			produces="application/json; charset=UTF-8")
	public ResponseEntity<List<CndSalonShopInfoVO>> getAll_ajax_filter(
			CndSalonShopInfoVO ShopInfoVO
			) {
		//log.info("----- shopmain_search Start ---"+ShopInfoVO.getPageNum());
		List<CndSalonShopInfoVO> list = null;
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json; charset=UTF-8");
		if (!ShopInfoVO.getUserLocalX().equals("") || !ShopInfoVO.getUserLocalY().equals("")) {
			//log.info("---search Start ---"  +ShopInfoVO.isSParking());
			list= service.searchShop(ShopInfoVO);

		} else {
			ShopInfoVO.setUserLocalX("37.62843");
			ShopInfoVO.setUserLocalY("127.07184");
			//log.info("---search Start ---   " + ShopInfoVO.getUserLocalX() + "  &&&&&  " + ShopInfoVO.getUserLocalY() + "   ");
			list= service.searchShop(ShopInfoVO);
		}
		
		return new ResponseEntity<List<CndSalonShopInfoVO>>(list,HttpStatus.OK);
		
	}

	// 내주변 root path
	@RequestMapping(value = "/shopmain")
	public String getAll_ajax(Model model) {
		
		return "/shop/shop_main.html";
	}
	
	/**
	 * 
	 * <pre>
	 * 샵 검색 메인
	 * </pre>
	 * @author <a href="simhyung777@naver.com">심현종</a></br>
	 * @date ${date}
	 * @version 1.0
	 * @since 2020-12-07
	 * @param 
	 * @return 
	 *  
	 */
	
	// 내주변 기본검색
	@ResponseBody
	@RequestMapping(value = "/shopmain_list", method = { RequestMethod.GET },
			produces="application/json; charset=UTF-8")
	public ResponseEntity<List<CndSalonShopInfoVO>> getAll_ajax_list(
			@RequestParam("userLocalX") String userLocalX,
			@RequestParam("userLocalY") String userLocalY,
			@RequestParam("pageNum") int pageNum) {
		List<CndSalonShopInfoVO> list = null;
		
		
		//log.info("---------shopmain_list Start--------------------");
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json; charset=UTF-8");
		if (!userLocalX.equals("") || !userLocalY.equals("")) {
			//log.info("---입력 좌표확인---" + userLocalX + "++++" + userLocalY);
			list= service.getAll(userLocalX, userLocalY,pageNum);

		} else {
			userLocalX += "37.62843";
			userLocalY += "127.07184";
			//log.info("---기본 좌표확인---   " + userLocalX + "  &&&&&  " + userLocalY + "   ");
			list= service.getAll(userLocalX, userLocalY,pageNum);
		}
		
		return new ResponseEntity<List<CndSalonShopInfoVO>>(list,HttpStatus.OK);
	}

	// 매장 상세정보
	@RequestMapping(value = "/shopdetail")
	public String getOne(@RequestParam("sCode") String sCode, Model model) {
		log.info("--------getOne Start---------" + sCode);
		
		model.addAttribute("shopinfo", service.getShopDetail(sCode));
		model.addAttribute("shopphoto", service.getShopProductPhoto(sCode));
		model.addAttribute("deginerinfo", service.getShopDesignerInfo(sCode));
		model.addAttribute("review", service.getReview(sCode));
		return "/shop/shop_detail.html";
	}
	
	//기본 내주변 검색(Max Page)
	@RequestMapping("/getPage")
	public ResponseEntity getPage() {
		log.info("getPage Start ------");
		int maxPage=0;
		maxPage=service.getPageNum();
		log.info("getPage result --------"+String.valueOf((maxPage)));
		return new ResponseEntity(maxPage,HttpStatus.OK);
	}
	
	//Filter 검색(Max Page)
	@RequestMapping(value = "/getFilterPage", method = { RequestMethod.GET },
			produces="application/json; charset=UTF-8")
	public ResponseEntity getFilterPage(CndSalonShopInfoVO ShopInfoVO) {
		log.info("getFilterPage Start ------");
		int maxPage=0;
		maxPage=service.getFilterPageNum(ShopInfoVO);
		log.info("getFilterPage result --------"+String.valueOf((maxPage)));
		return new ResponseEntity(maxPage,HttpStatus.OK);
	}
	
	//리뷰글의 이미지를 불러오기
	@RequestMapping(value = "/getReviewDetail", method = { RequestMethod.GET },
			produces="application/json; charset=UTF-8")
	public ResponseEntity<List<CndSalonReviewVO>> getReviewDetail(
			@RequestParam("rCode") String rCode
			) {
		log.info("review Detail Start ------");
		List<CndSalonReviewVO> list = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json; charset=UTF-8");
		list=service.getReviewPhoto(rCode);
		return new ResponseEntity<List<CndSalonReviewVO>>(list,HttpStatus.OK);
	}
	// 중복방지 TEST(IP기반)
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
		
	}// 기본 생성자 END

}// Class END
