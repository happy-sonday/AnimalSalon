package com.cndsalon.web.member;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;


@RestController//JSON타입으로 데이터를 주고받아오면서 ResponseBobdy를 빼고 사용할 수 있다
@Slf4j
public class CertificationController {
	
	@PostMapping("/check/id")
	public String checkId(@RequestBody Map<String, String> map) {
				
		log.debug("id 체킹중:"+map.get("id"));
		String msg = "";		
		if(map.get("id").equals("mrson1212")) {			
			msg="<b style='color: red'>이미 가입된 아이디입니다.</b>";
		}else {
			msg="<b style='color: blue'>멋진 아이디에요!</b>";
		}
		return msg;	
	}
		
	@PostMapping("/check/phone")
	public String checkPhone(@RequestBody Map<String, String> map) {
		
		
		String msg = "";		
		if(map.get("phone").equals("01096558120")) {
			
			msg="<b style='color: red'>이미 가입된 번호입니다."
					+"<br />아이디 또는 비밀번호 찾기를 이용해 주세요"
					+"</b>";
		}
		return msg;	
	}
		
	@PostMapping("/check/email")
	public String checkEmail(@RequestBody Map<String, String> map) {
		
		
		String msg = "";		
		
		System.out.println(map.get("email"));
		if(map.get("email").equals("son123@gmail.com")) {
			msg="<b style='color: red'>이미 가입된 이메일입니다."
					+"<br />아이디 또는 비밀번호 찾기를 이용해 주세요"
					+"</b>";
		}
		return msg;	
	}
		
	
	@PostMapping("/check/nickname")
	public String checkNickname(@RequestBody Map<String, String> map) {
		
		
		String msg = "";		
		if(map.get("nickname").equals("왓섭")) {
			
			msg="<b style='color: red'>사용중인 별명입니다.</b>";
		}else {
			msg="<b style='color: blue'>사용 가능한 별명입니다.</b>";
		}
		return msg;	
	}
	
		
}
