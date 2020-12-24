package com.cndsalon.domain.member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

	GUEST("ROLE_GUEST", "비회원"),
    CLIENT("ROLE_CLIENT", "고객"),
    DISGNER("ROLE_DISGNER", "디자이너"),
    DEVELOPER("ROLE_DEVELOPER", "개발자"),
    ADMIN("ROLE_ADMIN", "관리자");
    
    private final String key;
    private final String title;

}