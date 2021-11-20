package ru.jds.reboot.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.jds.reboot.server.repository.AtmCrudRepository;

public class AtmDetailsService implements UserDetailsService {

    @Autowired
    private AtmCrudRepository atmCrudRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new AtmDetails(
                atmCrudRepository
                        .findById(username)
                        .orElseThrow(() -> new UsernameNotFoundException("Could not find ATM"))
        );
    }
}
