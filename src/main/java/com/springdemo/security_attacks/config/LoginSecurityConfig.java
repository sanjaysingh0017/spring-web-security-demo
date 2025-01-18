package com.springdemo.security_attacks.config;

import com.giffing.bucket4j.spring.boot.starter.config.cache.SyncCacheResolver;
import com.giffing.bucket4j.spring.boot.starter.config.cache.jcache.JCacheCacheResolver;
import com.github.benmanes.caffeine.jcache.configuration.CaffeineConfiguration;
import com.springdemo.security_attacks.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import javax.sql.DataSource;
import java.time.Duration;
import java.util.OptionalLong;

@Configuration
public class LoginSecurityConfig {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public FreeMarkerViewResolver freemarkerViewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setCache(false);
        resolver.setPrefix("");
        resolver.setSuffix(".ftl");
        return resolver;
    }

    @Bean
    public FreeMarkerConfigurer freemarkerConfig() {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("classpath:/templates/");
        return freeMarkerConfigurer;
    }

//    @Bean
//    public CsrfTokenRepository csrfTokenRepository() {
//        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
//        repository.setHeaderName("X-XSRF-TOKEN");
//        return repository;
//    }


    //X-Frame-Options: deny
    //X-Frame-Options: sameorigin
    //X-Frame-Options: allow-from https://normal-website.com

    //Content-Security-Policy: frame-ancestors 'none';
    //Content-Security-Policy: frame-ancestors 'self';
    //Content-Security-Policy: frame-ancestors normal-website.com;
    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .headers(httpSecurityHeadersConfigurer ->
                    httpSecurityHeadersConfigurer.frameOptions(frameOptionsConfig -> frameOptionsConfig.disable()))
                // ...
//                .headers(httpSecurityHeadersConfigurer ->
//                        httpSecurityHeadersConfigurer.contentSecurityPolicy(contentSecurityPolicyConfig -> {
//                    contentSecurityPolicyConfig.policyDirectives("frame-ancestors 'none'");
//                }))
                .csrf(AbstractHttpConfigurer::disable).
                formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer.loginProcessingUrl("/login"))
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry.requestMatchers("/login").permitAll()
                                .anyRequest().authenticated())
                ;
        return http.build();
    }

    @Bean
    public SyncCacheResolver bucket4jCacheResolver() {
        final CachingProvider cachingProvider = Caching.getCachingProvider();
        CaffeineConfiguration<Object, Object> configuration = new CaffeineConfiguration<>();
        configuration.setExpireAfterWrite(OptionalLong.of(Duration.ofHours(1).toNanos()));
        configuration.setMaximumSize(OptionalLong.of(1000000));
        CacheManager cacheManager = cachingProvider.getCacheManager();
        cacheManager.createCache("rateLimiting", configuration);
        return new JCacheCacheResolver(cacheManager);
    }


//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.headers(httpSecurityHeadersConfigurer -> {
//            httpSecurityHeadersConfigurer.contentSecurityPolicy(contentSecurityPolicyConfig -> {
//                contentSecurityPolicyConfig.policyDirectives("script-src 'self' 'unsafe-inline' https://cdn.jsdelivr.net");
//            });
//        });
//        return http.build();
//    }

}
