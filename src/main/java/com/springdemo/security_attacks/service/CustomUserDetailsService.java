package com.springdemo.security_attacks.service;

import com.springdemo.security_attacks.dto.CustomUserDetails;
import com.springdemo.security_attacks.dto.ProfileDto;
import com.springdemo.security_attacks.entity.User;
import com.springdemo.security_attacks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
    }

    public boolean updateUser(CustomUserDetails customUserDetails, ProfileDto profileDto) {
        User user = customUserDetails.getUser();
        user.setName(profileDto.getName());
        user.setUsername(profileDto.getUsername());
        user.setMobileNumber(profileDto.getMobileNumber());
        user.setCreditCardNumber(profileDto.getCreditCardNumber());
        User save = userRepository.save(user);
        if (save != null) {
            return true;
        } else {
            return false;
        }
    }
}
