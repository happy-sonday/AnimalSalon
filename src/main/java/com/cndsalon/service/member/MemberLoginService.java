package com.cndsalon.service.member;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cndsalon.domain.member.CustomUserDetails;
import com.cndsalon.repository.member.loginRepository;
import com.cndsalon.web.dto.member.DemoMemberDto;


@Service
public class MemberLoginService implements UserDetailsService{

	@Autowired
	private loginRepository loginRepository;
	
	//암호화 하여 회원정보 저장
		public void joinUser(DemoMemberDto memberDto) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
			loginRepository.save(memberDto);
		}
	
		/*
		
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		DemoMemberDto userEntity=loginRepository.findByEmail(username);
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		authorities.add(new SimpleGrantedAuthority(  userEntity.getRole()) );
		
		return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
		

	}
	*/
		
		@Override
	    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	  
			System.out.printf("loadUserByUsername에서 받은 인자---%s \n", username);
			System.out.println("loadUserByUsername() 실행");		
	    	CustomUserDetails userDetails  = loginRepository.getOne(username);
	    	//System.out.printf("userDetails에 담긴 정보: %s \n", userDetails.toString());
	    
			if(userDetails==null) {
				System.out.println("userDetails null값 확인");
				throw new UsernameNotFoundException(username);
				
			}
			
			return userDetails;
		}

	
	
	

}
