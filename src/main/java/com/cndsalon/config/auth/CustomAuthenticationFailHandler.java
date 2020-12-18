package com.cndsalon.config.auth;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.Data;

@Component
public class CustomAuthenticationFailHandler implements AuthenticationFailureHandler {

	private final String DEFAULT_FAILURE_URL = "/member/login?error";


	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		//System.out.println(exception.getMessage());
		//response.sendRedirect(request.getContextPath()+"/user/login?error");
		
		//아래 내용은 허용이안됨...
		//request.setAttribute("errorMsg", exception.getMessage());
		//request.getRequestDispatcher("/user/login").forward(request, response);
		


		
		
		/*
		
		if (exception instanceof AuthenticationServiceException) {
			request.setAttribute("loginFailMsg", "존재하지 않는 사용자입니다.");
		
		} else if(exception instanceof BadCredentialsException) {
			request.setAttribute("loginFailMsg", "아이디 또는 비밀번호가 틀립니다.");
			
		} else if(exception instanceof LockedException) {
			request.setAttribute("loginFailMsg", "잠긴 계정입니다..");
			
		} else if(exception instanceof DisabledException) {
			request.setAttribute("loginFailMsg", "비활성화된 계정입니다..");
			
			//서비스인터페이스로 구현해서 에러메시지를 만들고 넘기면된다.
			 
			
		} else if(exception instanceof AccountExpiredException) {
		//서비스인터페이스로 구현해서 에러메시지를 만들고 넘기면된다.
			request.setAttribute("loginFailMsg", "만료된 계정입니다..");
			
		} else if(exception instanceof CredentialsExpiredException) {
			request.setAttribute("loginFailMsg", "비밀번호가 만료되었습니다.");
		}
		
		
		// 로그인 페이지로 다시 포워딩
		RequestDispatcher dispatcher = request.getRequestDispatcher(request.getContextPath()+"/user/login");
		dispatcher.forward(request, response);
		
		*/

		
		System.out.println("CustomFailureHandler 동작");
		
		String errorMessage=null;
		
		if(exception instanceof BadCredentialsException || exception instanceof UsernameNotFoundException ||exception instanceof DisabledException) {
			
			//없는 계정, 비밀번호 또는 아이디만 틀렸는지 구분지어 메시지를 출력하는 것은 보안에 취약해짐
			errorMessage = "아이디(이메일)나 비밀번호가 맞지 않습니다.다시 확인해 주세요.";
			
		}
		else if(exception instanceof LockedException ) {
			
			errorMessage = "비정상적인 접근시도로 계정이 비활성화 되었습니다.관리자에게 문의하세요.";
			//서비스인터페이스로 구현해서 에러메시지를 만들고 넘기면된다.
		}		
		
		else if(exception instanceof CredentialsExpiredException) {
			//서비스인터페이스로 구현해서 에러메시지를 만들고 넘기면된다.
			errorMessage = "비밀번호 유효기간이 만료되었습니다.변경해 주시기 바랍니다.";			
			
		}
		else if( exception instanceof AccountExpiredException) {
			//서비스인터페이스로 구현해서 에러메시지를 만들고 넘기면된다.
			errorMessage = "장기간 미접속으로 휴면계정처리 되었습니다.관리자에 문의하세요.";
		}
		
		//request.setAttribute("errorMessage", errorMessage);
		System.out.println("========================Failhandler 결과");
	
		//request.getRequestDispatcher(DEFAULT_FAILURE_URL).forward(request, response);
		//response.sendRedirect(request.getContextPath()+"/user/login?error");
		
		
				
		
		String username=request.getParameter("username");
		System.out.println("FailHandler의 username: "+username);
		System.out.println("에러결과값(한글):"+errorMessage);

		request.setAttribute("errorMessage", errorMessage);
		request.getRequestDispatcher(DEFAULT_FAILURE_URL).forward(request, response);
		

	}
}

		
		
