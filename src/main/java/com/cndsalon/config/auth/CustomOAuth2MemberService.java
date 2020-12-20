//package com.cndsalon.config.auth;
//
//
//import lombok.RequiredArgsConstructor;
//
//import java.util.Collections;
//
//import javax.servlet.http.HttpSession;
//
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
//import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
//import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Service;
//
//import com.cndsalon.config.auth.dto.OAuthAttributes;
//import com.cndsalon.config.auth.dto.SessionMember;
//import com.cndsalon.domain.member.Member;
//import com.cndsalon.repository.member.MemberRepository;
//
//
//
//@RequiredArgsConstructor
//@Service
//public class CustomOAuth2MemberService implements OAuth2UserService<OAuth2UserRequest, OAuth2User>{
//
//	private final MemberRepository memberRepository;
//    private final HttpSession httpSession;
//
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        OAuth2UserService delegate = new DefaultOAuth2UserService();
//        OAuth2User oAuth2User = delegate.loadUser(userRequest);
//
//        String registrationId = userRequest.getClientRegistration().getRegistrationId();
//        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
//                .getUserInfoEndpoint().getUserNameAttributeName();
//
//        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
//
//        Member user = saveOrUpdate(attributes);
//        httpSession.setAttribute("user", new SessionMember(user));
//
//        return new DefaultOAuth2User(
//                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
//                attributes.getAttributes(),
//                attributes.getNameAttributeKey());
//    }
//
//
//    private Member saveOrUpdate(OAuthAttributes attributes) {
//    	Member user = memberRepository.findByEmail(attributes.getEmail())
//                .map(entity -> entity.update(attributes.getName(), attributes.getProfile()))
//                .orElse(attributes.toEntity());
//
//        return memberRepository.save(user);
//    }
//
//    
//
//        
//    }
