package com.kentoes.jwtAuth.models.entities.user;

import com.kentoes.jwtAuth.models.entities.role.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private Boolean enabled;

    public UserDetailsImpl(String username, String password, Collection<? extends GrantedAuthority> authorities, Boolean enabled) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName().name()));
        }
        return new UserDetailsImpl(
                user.getUsername(),
                user.getPassword(),
                authorities,
                user.isEnabled());
    }
}
