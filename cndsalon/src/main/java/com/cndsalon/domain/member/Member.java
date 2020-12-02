package com.cndsalon.domain.member;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;




@Getter
@NoArgsConstructor
public class Member {


    private Long id;


    private String name;


    private String email;


    private String picture;


    private Role role;

    @Builder
    public Member(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public Member update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}