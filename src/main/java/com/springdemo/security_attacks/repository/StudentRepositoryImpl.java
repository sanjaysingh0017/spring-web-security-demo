package com.springdemo.security_attacks.repository;

import com.springdemo.security_attacks.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class StudentRepositoryImpl implements StudentRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Student> findStudent(String studentId) {
        List<Student> resultList = entityManager.createNativeQuery("Select * from Student where id='"+studentId+"'", Student.class).getResultList();
//        List<Student> resultList = entityManager.createQuery("Select s from Student s where s.id=:id", Student.class)
//                .setParameter("id", studentId).getResultList();
        return resultList;
    }

    @Override
    public Integer deleteStudent(String studentId) {
        Integer numberOfDeletedStudents = entityManager.createNativeQuery("Delete from Student where id='"+studentId+"'", Student.class).executeUpdate();
//        List<Student> resultList = entityManager.createQuery("Delete from Student where id=:id", Student.class)
//                .setParameter("id", studentId).getResultList();
        return numberOfDeletedStudents;
    }
}
