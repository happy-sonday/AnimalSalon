package com.cndsalon.web.member;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MemberController {
	

	//private final HttpSession httpSession;
		
	//Main(전체 글 목록)
	@GetMapping("/login")
	 public String index() {
	

			
		return "/member/login";
	}
	
	//약관
	@GetMapping("/terms")
	public String terms() {
	
		return "/member/terms";
	}
	
	//아이디 비밀번호 찾기
	@GetMapping("/findIdPwd")
	public String findIdPwd() {
		return "/member/findIdPwd";
	}
	
	//회원가입 양식폼
	@GetMapping("/signup")
	public String signUp() {
		return "/member/signupForm";
	}
	
	//회원가입 양식 저장 요청
	@PostMapping("/join")
	public String  resultJoin() {
		return "/member/join";
	}
	
	//아이디회원가입양식 폼 요청
	@GetMapping("/findInfo")
	public String findInfo() {
		
		return "/member/findInfoForm";
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

}
