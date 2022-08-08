package com.kentoes.jwtAuth.services.user;

import com.kentoes.jwtAuth.models.entities.user.User;
import com.kentoes.jwtAuth.models.entities.user.UserDetailsImpl;
import com.kentoes.jwtAuth.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Primary
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("username not found!"));
        return UserDetailsImpl.build(user);
    }
}
