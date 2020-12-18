package com.cndsalon.web.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cndsalon.service.member.MemberLoginService;
import com.cndsalon.web.dto.member.DemoMemberDto;

import lombok.AllArgsConstructor;

@Controller
public class LoginController {
	

	@Autowired
	private MemberLoginService loginService;
	
	
	   // 메인 페이지
    @GetMapping("/test")
    public String index() {
        return "/test/index";
    }

    // 회원가입 페이지
    @GetMapping("/test/signup")
    public String dispSignup() {
        return "/test/signup";
    }

    // 회원가입 처리
    @PostMapping("/test/user/signup")
    public String execSignup(DemoMemberDto memberDto) {
    	loginService.joinUser(memberDto);

        return "redirect:/test/login";
    }

    // 로그인 페이지
    @GetMapping("/test/login")
    public String dispLogin() {
        return "/test/login";
    }

    // 로그인 결과 페이지
    @GetMapping("/test/login/result")
    public String dispLoginResult() {
        return "/test/loginSuccess";
    }

    // 로그아웃 결과 페이지
    @GetMapping("/test/logout/result")
    public String dispLogout() {
        return "/test/logout";
    }

    // 접근 거부 페이지
    @GetMapping("/test/denied")
    public String dispDenied() {
        return "/test/denied";
    }

    // 내 정보 페이지
    @GetMapping("/test/info")
    public String dispMyInfo() {
        return "/test/myinfo";
    }

    // 어드민 페이지
    @GetMapping("/test/admin")
    public String dispAdmin() {
        return "/test/admin";
    }
}
	