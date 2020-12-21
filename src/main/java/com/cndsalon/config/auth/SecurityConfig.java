package com.cndsalon.config.auth;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.cndsalon.domain.member.Role;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final CustomOAuth2MemberService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
                    .antMatchers("/",  "/static/**", "/member/**", "/check/**", "/shop/**", "/bookinghome/**", "/payments/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.CLIENT.name())
                    .anyRequest().authenticated()
                .and()
                	.oauth2Login().loginPage("/login")
                .and()
                    .logout().logoutSuccessUrl("/")
                .and()
                    .oauth2Login().userInfoEndpoint().userService(customOAuth2UserService);
    }
}
