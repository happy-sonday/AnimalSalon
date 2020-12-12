package com.cndsalon.web.member;


import java.net.http.HttpRequest;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.RequestScope;

import com.cndsalon.domain.member.DemoMember;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MemberController {
	

		
	@GetMapping("/member/login")
	 public String checkId() {
		
		return "/member/login";
	}
	
	//약관
	@GetMapping("/member/terms")
	public String terms() {
	
		return "/member/terms";
	}
	
	//아이디 비밀번호 찾기
	@GetMapping("/member/findIdPwd")
	public String findIdPwd() {
		return "/member/findIdPwd";
	}
	
	//회원가입 양식폼
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
	/*
	//글 저장
	@GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

	//글 업데이트
    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
    */

	
	@RequestMapping("/member/demo")
	public String test() {
		
		return"/member/DemoUpload";
	}
	
}
