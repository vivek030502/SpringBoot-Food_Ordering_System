package com.onlinefoodordering.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.onlinefoodordering.model.USER_ROLE;
import com.onlinefoodordering.model.User;
import com.onlinefoodordering.repository.UserRepository;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomerUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with Email " + username);
        }

        USER_ROLE role = user.getRole();
        if (role == null) {
            role = USER_ROLE.ROLE_CUSTOMER;
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(role.name().replace("ROLE_", "")) // Removes ROLE_ prefix
                .build();
    }
}
