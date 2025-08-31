package com.apartment.management.security.service;

import com.apartment.management.entity.AuthorityRole;
import com.apartment.management.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

/**
 * A CustomUserDetails.
 */
public class CustomUserDetails implements UserDetails {

    private final String username;

    private final String password;

    private final Collection<GrantedAuthority> authorities;

    private final boolean enabled;

    public CustomUserDetails(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.enabled = user.getStatus();
        this.authorities = new HashSet<>();
        for (AuthorityRole authorityRole : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(authorityRole.getRole()));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
