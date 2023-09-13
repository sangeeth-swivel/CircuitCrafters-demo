package com.taimurain.service;

import com.taimurain.configuration.CustomUserDetails;
import com.taimurain.domain.entity.User;
import com.taimurain.exception.InvalidLoginException;
import com.taimurain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByMobileNo(username);
        return optionalUser.map(CustomUserDetails::new)
                .orElseThrow(() -> new InvalidLoginException("User not found for username: " + username));
    }
}
