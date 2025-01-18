package com.springdemo.security_attacks.service;

import com.springdemo.security_attacks.dto.CustomUserDetails;
import com.springdemo.security_attacks.dto.ProfileDto;
import com.springdemo.security_attacks.entity.User;
import com.springdemo.security_attacks.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        CustomUserDetails customUserDetails = new CustomUserDetails(user);
        Authentication authentication= new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities()) ;
        SecurityContextHolder.getContext().setAuthentication(authentication);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return customUserDetails;
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

    public String getUsername(Authentication authentication) {
        CustomUserDetails  userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getUser().getUsername();
    }
}
