package com.energizeglobal.assignment.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author Mehdi Chitforoosh
 * @since 1.0.0
 * Custom user details
 */
public class AtmUser extends User {

    private final Long atmId;

    public AtmUser(String username, String password, Collection<? extends GrantedAuthority> authorities, Long atmId) {
        super(username, password, authorities);
        this.atmId = atmId;
    }

    public Long getAtmId() {
        return atmId;
    }

}
