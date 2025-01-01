package com.springdemo.security_attacks.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.security.core.GrantedAuthority;

@Entity
public class Authorities implements GrantedAuthority {

    @Id
    @Column(unique = true, nullable = false)
    private String username;
    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
