package com.springdemo.security_attacks.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Repository
@EnableTransactionManagement
public class UserRepositoryImpl implements UserRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public Integer findUser(String userId) {
        int i = entityManager.createNativeQuery("Delete from User where id=" + userId).executeUpdate();
        return i;
    }
}
