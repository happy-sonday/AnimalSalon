package com.cndsalon.config.auth.dto;

import java.util.Map;

import com.cndsalon.domain.member.Member;
import com.cndsalon.domain.member.Role;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthAttributes {
	
	 private Map<String, Object> attributes;
	    private String nameAttributeKey;
	    private String name;
	    private String email;
	    private String profile;

	    @Builder
	    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String profile) {
	        this.attributes = attributes;
	        this.nameAttributeKey = nameAttributeKey;
	        this.name = name;
	        this.email = email;
	        this.profile = profile;
	    }

	    public static OAuthAttributes of(String registrationId, String memberNameAttributeName, Map<String, Object> attributes) {
	        if("naver".equals(registrationId)) {
	            return ofNaver("id", attributes);
	        }

	        return ofGoogle(memberNameAttributeName, attributes);
	    }

	    private static OAuthAttributes ofGoogle(String memberNameAttributeName, Map<String, Object> attributes) {
	        return OAuthAttributes.builder()
	                .name((String) attributes.get("name"))
	                .email((String) attributes.get("email"))
	                .profile((String) attributes.get("profile"))
	                .attributes(attributes)
	                .nameAttributeKey(memberNameAttributeName)
	                .build();
	    }

	    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
	        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

	        return OAuthAttributes.builder()
	                .name((String) response.get("name"))
	                .email((String) response.get("email"))
	                .profile((String) response.get("profile_image"))
	                .attributes(response)
	                .nameAttributeKey(userNameAttributeName)
	                .build();
	    }

	    public Member toEntity() {
	        return Member.builder()
	                .name(name)
	                .email(email)
	                .profile(profile)
	                .role(Role.GUEST)
	                .build();
	    }
	    
}
