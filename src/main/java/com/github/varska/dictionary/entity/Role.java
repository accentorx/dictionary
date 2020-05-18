package com.github.varska.dictionary.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER("ROLE_USER"), ADMIN("ROLE_ADMIN");

    private String role;

    Role() {
    }

    Role(String role) {
        this.role = role;
    }


    @Override
    public String getAuthority() {
        return role;
    }
}
