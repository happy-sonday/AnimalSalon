package com.cndsalon.domain.member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

	FORBIDDEN("ROLE_FORBIDDEN", "접근제한"),
	GUEST("ROLE_GUEST", "비회원"),
    CLIENT("ROLE_CLIENT", "고객"),
    DISGNER("ROLE_DISGNER", "디자이너"),
    SHOP("ROLE_SHOP", "매장"),
    ADMIN("ROLE_ADMIN", "관리자"),
    DROP("ROLE_DROP", "탈퇴");
    
    private final String key;
    private final String title;

}