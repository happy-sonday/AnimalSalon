package com.cndsalon.domain.member;

import java.security.Timestamp;



import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * <pre>
 * 개요: 회원가입시 저장될 Member 정보
 * </pre>
 * @author <a href="mailto:mrsmiler1106@gamil.com">손우섭</a><br>
 * @date 2020. 12. 18. 

 */
@Getter
@Setter
@ToString
public class DemoMember {
	

	private String id;//아이디
	private String password;//패스워드
	private String name;//이름
	private String zip;//우편번호
	private String address;//집주소
	private String phone;//휴대번호
	private String email;//이메일
	private String profile;//프로필사진
	private String nickname;//별명
	
	//CustomUserDetails
	private String authority; //CLIENT, DESIGNER, DEVELOPER, ADMIN dafault=CLIENT
	private int status; // 0=locked : 1=enabled : 2=passwordExpired : 3=accountExpired 4=disabled dafault=1
	
	//추가정보	
	private Timestamp regDate;//가입일
	private int acceptAd; //광고수신 동의
	private int actNickname;//별명으로 활동할지 여부 0=flase 1=true	

	

}
