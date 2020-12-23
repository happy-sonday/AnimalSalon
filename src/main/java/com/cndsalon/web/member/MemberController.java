package com.cndsalon.web.member;


import java.net.http.HttpRequest;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.RequestScope;

import com.cndsalon.domain.member.DemoMember;
import com.cndsalon.service.member.CertificationService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MemberController {
	

	
	@Autowired
	CertificationService chkService;
		
	//로그인페이지 요청
	@RequestMapping("/member/login")
	 public String disLoginForm( HttpServletRequest request) {
		
				// 요청 시점의 사용자 URI 정보를 Session의 Attribute에 담아서 전달(잘 지워줘야 함)
				// 로그인이 틀려서 다시 하면 요청 시점의 URI가 로그인 페이지가 되므로 조건문 설정
				String uri = request.getHeader("Referer");
				
				if (!uri.contains("/member/login")) {
					request.getSession().setAttribute("prevPage",
							request.getHeader("Referer"));				
				
				}
				
		return "/member/login";
	}
	
	//약관 페이지 요청
	@GetMapping("/member/terms")
	public String terms() {
	
		return "/member/terms";
	}
	
	//아이디 비밀번호 찾기
	@GetMapping("/member/findIdPwd")
	public String findIdPwd() {
		return "/member/findIdPwd";
	}
	
	//회원가입 양식폼 요청
	@GetMapping("/member/signup")
	public String signUp(Model model) {
		
		/*다음 페이지에서 th:object 속성을 사용하려면 비어있는 개체를 던져줘야한다*/		
		model.addAttribute("demoMember", new DemoMember());
		
		return "/member/signupForm";
	}
	

	
	//아이디회원가입양식 폼 요청
	@GetMapping("/member/findInfo")
	public String findInfo() {
		
		return "/member/findInfoForm";
	}
	
	//회원가입 입력 결과 요청
	@GetMapping("/member/welcome")
	public String welcome() {
		
		
		return "/member/join";
	}
	
	//인증번호 받기
	@GetMapping("/getCrftNum")
	public String getCrftNum() {
		
		return "/util/getCrftNum";
	}
	
	//이미지 업데이트
	@PostMapping("/updateImg")
	public String updateImg() {
		
		return "redirect:/";
	}

	
	@RequestMapping("/member/demo")
	public String test() {
		
		return"/member/DemoUpload";
	}
	
	
	//로그인 처리 결과url 성공시 메인페이지 틀리면 redirect
	@RequestMapping("/member/login/result")
	public String loginChk() {

	return "/";
	}
	

	
	//로그아웃 결과url 성공시 메인페이지 틀리면 redirect
	/*
	@RequestMapping("/member/logout")
	public String dologoutChk(HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null) {
			
			System.out.println("세션 삭제 실행");
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		

	return "redirect:/";
	}
	*/
	
	
	//admin 메인 페이지 호출
	@GetMapping("/member/admin")
	public String disAdminMain() {
		
		return "/member/adminMian";
	}
	
	
	//권한이 없는 사용자가 권한 요청시 폼 출력
	@GetMapping("/member/denied")
	public String disDenied() {
		
		return "/member/denied";
	}
	
}
