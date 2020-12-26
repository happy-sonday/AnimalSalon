package com.cndsalon.config.auth;


import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final CustomOAuth2MemberService customOAuth2UserService;
    
    @Override
    public void configure(WebSecurity web) throws Exception
    {
        // static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**","/messages/**");
    }

    @Override
	  protected void configure(HttpSecurity http) throws Exception {
    	http.authorizeRequests()
    		//페이지 권한 설정
    		.antMatchers("/admin/**").hasRole("ADMIN")
    		.antMatchers("/member/myinfo").hasRole("CLIENT")
    		.antMatchers("/member/login/**", "/member/logout/**").anonymous()
    		.antMatchers("/**", "/check/**", "/booking/**", "/bookinghome/**", "/shop/**", "/payments/**").permitAll()
            //.anyRequest().authenticated()
    			.and()
    				.oauth2Login().loginPage("/login")
    			.and()
                //.oauth2Login().userInfoEndpoint().userService(customOAuth2UserService)		
                //로그인 설정
    				.formLogin()
    				.loginPage("/member/login")//폼을 출력할 요청 url
	                //spring security가 기본으로 제공하는 설정값 해당 loginPage의 name속성과 동일해야함
	                .usernameParameter("username")
	                .passwordParameter("password")
	                ///////////////////////////////////////
	                .loginProcessingUrl("/member/login/check")//controller에 따로 설정하지 않아도 spring security가 form에 설정된 url을가지고 점검 
	                .defaultSuccessUrl("/")
                    .failureHandler(failureHandler()) 
                    .successHandler(sucessHandler())//로그인 직후의 Authentication제어 핸들러
	                .permitAll()
                .and()
                //소셜로그인 설정
                	.authorizeRequests()                    
                	.antMatchers("/api/v1/**").hasRole("CLIENT")
                	.anyRequest().authenticated()                  
                .and() 
                // 403 예외처리 핸들링
                	.exceptionHandling().accessDeniedPage("/member/denied")
                .and()
                //로그아웃 설정
                	.logout()            	
                	.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))//사용자 관점 로그아웃 요청 url            		
                	.invalidateHttpSession(true)//로그아웃 시 세션 제거
                	.deleteCookies("JSESSIONID")//쿠키 제거            		            	
                	.logoutSuccessUrl("/")//Controller에 mapping될 url
                	.clearAuthentication(true)//권한정보 제거            		
                	.permitAll()
                .and()    	        		
            		//중복로그인 방지 설정
            		.sessionManagement()
            		.maximumSessions(1) //세션 허용 갯수
            		.expiredUrl("/member/login")//세션 만료시 이동할 페이지
            		.maxSessionsPreventsLogin(true);//동일사용자 로그인 방지             

    }
    
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.authenticationProvider(authenticationProvider());
    }    
    
    
    //======bean 등록 : 사용하려면 HttpSecurity 와 AuthenticationManagerBuilder 에 선언해줘야 함
    @Bean 
    public AuthenticationProvider authenticationProvider() {
    	return new CustomAuthenticationProvider();
    }
        		    
    @Bean
    public AuthenticationFailureHandler failureHandler() {
    	return new CustomAuthenticationFailHandler();
    }
    
    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
    	return new BCryptPasswordEncoder();
    }
    
    
    
    @Bean
    public AuthenticationSuccessHandler sucessHandler() {
    	return new CustomAuthenticationSuccessHandler();
    }
    
    
}