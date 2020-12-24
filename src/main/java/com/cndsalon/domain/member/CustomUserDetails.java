package com.cndsalon.domain.member;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

/**
 * <pre>
 * 개요:
 * </pre>
 * @author <a href="mailto:mrsmiler1106@gamil.com">손우섭</a><br>
 * @date 2020. 12. 16. 
 */


@Data
public class CustomUserDetails implements UserDetails{

	private static final long serialVersionUID = -8076351178469287142L;
	//인터페이스가 아니라 해당 클래스 커서 위에서 ctr+1 누르면 생성
	//분산처리환경에서 유일한(Unique)클래스라는 것을 증명하기 위한 신분증
	//모든 직렬화 가능 클래스는 명시적으로 serialVesionUID를 생성하는것을 추천하고있음
	
	private String id;
	private String email;
	private String password;
	private String authority;
	private int status;
	private String nickname;
	private int actNickname;
	//private int enabled;
	//private boolean accountExpired;
	//private int passwordExpired;
	//private int accountExpired;
	//private int locked;
	//private String name;
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		
		
		ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
		auth.add(new SimpleGrantedAuthority(this.authority));
		return auth;
	}

	@Override
	public String getPassword() {
	
		return this.password;
	}

	@Override
	public String getUsername() {

		return this.id;
	}

	@Override
	public boolean isAccountNonExpired() {
		//휴면계정으로 전환
		return this.status==1 ? true : false;
	}

	@Override
	public boolean isAccountNonLocked() {
		//접근제한 계정
		return this.status==0?false : true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		//패스워드 변경요청 관련 멤법변수
		return this.status==2?false : true;
	}

	@Override
	public boolean isEnabled() {
		//false일 경우 삭제된 계정이라고 칭함
		return this.status==3? false : true;
	}

	
	
}
