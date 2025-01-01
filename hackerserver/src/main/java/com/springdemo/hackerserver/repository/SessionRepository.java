package com.springdemo.hackerserver.repository;

import com.springdemo.hackerserver.entity.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<SessionEntity, Long> {

}
