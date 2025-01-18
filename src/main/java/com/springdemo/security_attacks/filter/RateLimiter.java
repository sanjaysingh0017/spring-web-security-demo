//package com.springdemo.security_attacks.filter;
//
//import com.github.benmanes.caffeine.cache.CacheLoader;
//import com.github.benmanes.caffeine.cache.Caffeine;
//import com.github.benmanes.caffeine.cache.LoadingCache;
//import com.springdemo.security_attacks.dto.CustomUserDetails;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.checkerframework.checker.nullness.qual.Nullable;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.time.Duration;
//
//@Component
//public class RateLimiter extends OncePerRequestFilter {
//
//    private static final Integer MAX_REQUEST = 2;
//
//    private LoadingCache<String, Integer> requestPerUser;
//
//    RateLimiter() {
//        super();
//        requestPerUser = Caffeine.newBuilder().expireAfterWrite(Duration.ofMinutes(1)).build(new CacheLoader<String, Integer>() {
//            @Override
//            public @Nullable Integer load(String s) throws Exception {
//                return 0;
//            }
//        });
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        HttpServletResponse httpServletResponse = response;
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
//        String username = customUserDetails.getUsername();
//        if( isMaximumRequestExceeded(username)) {
//            httpServletResponse.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
//            httpServletResponse.getWriter().write("Too many requests");
//            return;
//        }
//        filterChain.doFilter(request, response);
//    }
//
//    private boolean isMaximumRequestExceeded(String username) {
//        Integer request = 0;
//        request = requestPerUser.get(username);
//        if( request != null) {
//            if(request >= MAX_REQUEST) {
//                requestPerUser.asMap().remove(username);
//                requestPerUser.put(username, request);
//                return true;
//            }
//        }else {
//            request = 0;
//        }
//        request++;
//        requestPerUser.put(username,request);
//        return false;
//    }
//
//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//        return !request.getServletPath().contains("api/profile");
//    }
//}
