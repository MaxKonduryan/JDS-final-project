package ru.jds.reboot.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.jds.reboot.server.entity.AtmEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AtmDetails implements UserDetails {

    String ROLE_PREFIX = "ROLE_";

    private final AtmEntity atm;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authority = new ArrayList<>(2);
        authority.add(new SimpleGrantedAuthority(atm.getRole()));
        authority.add(new SimpleGrantedAuthority(ROLE_PREFIX + atm.getRole()));
        return authority;
    }

    @Override
    public String getPassword() {
        return atm.getCode();
    }

    @Override
    public String getUsername() {
        return atm.getName();
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
        return true;
    }
}
