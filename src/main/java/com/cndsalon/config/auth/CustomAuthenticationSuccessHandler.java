package com.cndsalon.config.auth;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Service;

@Service
public class CustomAuthenticationSuccessHandler  implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
				
		
				// IP, 세션 ID
				WebAuthenticationDetails web = (WebAuthenticationDetails) authentication.getDetails();
				System.out.println("IP : " + web.getRemoteAddress());
				System.out.println("Session ID : " + web.getSessionId());
				
				// 인증 ID
				System.out.println("name : " + authentication.getName());
				
				// 권한 리스트
				List<GrantedAuthority> authList = (List<GrantedAuthority>) authentication.getAuthorities();
				System.out.print("권한 : ");
				for(int i = 0; i< authList.size(); i++) {
					System.out.print(authList.get(i).getAuthority() + " ");
				}
				System.out.println();
				
				
				// 세션 Attribute 확인
				Enumeration<String> list = request.getSession().getAttributeNames();
				while (list.hasMoreElements()) {
					System.out.println("세션 Attribute 확인:"+list.nextElement());
				}
				
				//일반적으로 사용자 권한외의 페이지를 요청할경우 서블리스올 바로보내지않고 해당 요청을 가로채
				//로그인 페이지요청으로 바꿔 서블릿에 전달되어 로그인폼 제출로 응답한다.
				
				
				//접근권한 없는 페이지를 요청할 경우 사용자가 원래 요청했던 URL정보를 저장
				RequestCache requestCache = new HttpSessionRequestCache();
				SavedRequest savedRequest = requestCache.getRequest(request, response);
				System.out.println("---SucessHandler에 처리 및 저장된 요청 URI:"+savedRequest);
				
				//default URI
				String uri = "/";
		

				//1. 로그인 버튼을 눌러 접속했을 이전 머무른 url을 저장하여 다시 redirect 하기위함
				String prevPage = (String) request.getSession().getAttribute("prevPage");
				if (prevPage != null) {
					request.getSession().removeAttribute("prevPage");
				}
				
				
				//2. 접근권한이 없는 페이지를 요청할 경우 강제 인터셉트
				if (savedRequest != null) {
					
					uri = savedRequest.getRedirectUrl();
					
					//그대로 사용하는 경우 session 객체라 메모리에 남아 상주하여 누수를 발생하고 이로 인한 보안 취약점이 발생한다
					//따라서 문자열에 저장후 정보를 담은 session객체는 삭제해준다.
					requestCache.removeRequest(request, response);

					System.out.println(uri);
				}
				//3. 직접 로그인 페이지에 접속하는 경우
				else if(prevPage != null && !prevPage.equals("")) {
					uri = prevPage;
				}				
				
				
								
				//case에 따른 uri주소로 redirect
				response.sendRedirect(uri);
		
	}

}
