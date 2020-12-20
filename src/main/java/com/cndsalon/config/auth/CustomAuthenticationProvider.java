package com.cndsalon.config.auth;

import javax.security.auth.login.CredentialException;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.cndsalon.domain.member.CustomUserDetails;
import com.cndsalon.service.member.MemberLoginService;


import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	private MemberLoginService loginService;

	@Autowired
	private BCryptPasswordEncoder encoder;
	//Authentication 은 화면에서 사용자가 입력한 정보를 id(email)과 password는 각 getPrincipal()과 getCredential()로
	//UserDeatilsService를 구현한 CustmoUserDetailservice에서 가져온 유저정보와 match하여 예외처리할 예정이다
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		System.out.printf("CusotomAuthenticationProvider.java 실행---로그인에서 입력한 id 또는 email:%s \n",authentication.getPrincipal());
	
		String username = (String)authentication.getPrincipal();//로그인 정보창에서 가져온 id 또는 email
		String password=(String)authentication.getCredentials();//일반적으로 password


		CustomUserDetails userDetails =(CustomUserDetails)loginService.loadUserByUsername(username);
		System.out.printf("loadUserByUsername에서 받은 userDetails의 정보---%s \n",userDetails.toString());
				
		//로그인 입력정보를 이메일로이나 아이디 정보를 받을 수 있기때문에 비교할 대상을 삼항연산자로 지정
		String targetUsername = username.contains("@")?userDetails.getEmail():userDetails.getUsername();
		System.out.println("targetUsername 정보:"+targetUsername);
		

		
		
		//DB에 정보가 없거나 입력정보가 일치하지 않는경우		
		if( userDetails==null || !username.equals(targetUsername) ) {
			System.out.println("계정이 없음 : BadCrendtials 날라감");
			throw new BadCredentialsException(username);
			
		}
		//주의 저장될때 패스워드가 암호화 되어있기때문에 equals를 쓰는것이아니라 matches()로 비교해야한다	
		else if(!encoder.matches(password, userDetails.getPassword())) {
			System.out.println("비밀번호 불일치:BadCrendtials 날라감");
			throw new BadCredentialsException(username);
		}
	

			
		
		//잠긴 계정일 경우
		else if(!userDetails.isAccountNonLocked()) {
			
			System.out.println("LockedException 날라감");
			throw new LockedException(username);
		}
		
		//비밀번호가 만료(3개월경과 된 경우)
		else if(!userDetails.isCredentialsNonExpired()) {
			
			System.out.println("CredentialsExpiredException 날라감");
			throw new CredentialsExpiredException(username);
		}
		
		//삭제된 계정
		else if(!userDetails.isEnabled()) {
			System.out.println("DisabledException 날라감");
			throw new DisabledException(username);
		}
		
	
		
		//filter과정이 끝나면 외부에서도 비밀번호를 조회못하도록 null처리
		userDetails.setPassword(null);

	
	
		//위 과정들에서 비밀번호 비교가 끝나면 홈페이간 외부에서 조회 못 하도록 null처리
		Authentication newAuth = 
		new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		//보통id(username)을 넘기지만 내부 멤버변수를 활용하고자하면 객체자체를 넘겨도 된다.
		//
		
		return newAuth;

	}

	
		
	
	
	@Override
	public boolean supports(Class<?> authentication) {		
		//spring security가 요구하는 UsernamePasswordAuthenticationToken이 맞는지 검사
		//확인후 true를 반환해야 최종적으로 인증 성공
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	
	
	

}
