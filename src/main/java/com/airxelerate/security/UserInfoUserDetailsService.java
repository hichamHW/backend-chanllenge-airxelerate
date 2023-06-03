package com.airxelerate.security;

import com.airxelerate.entity.User;
import com.airxelerate.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userInfo = repository.findByUserName(username);
        return (UserDetails) userInfo
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));

    }
}
