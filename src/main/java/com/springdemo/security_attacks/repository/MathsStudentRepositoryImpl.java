package com.springdemo.security_attacks.repository;

import com.springdemo.security_attacks.entity.MathsStudent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class MathsStudentRepositoryImpl implements MathsStudentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<MathsStudent> findStudent(String studentId) {
        List<MathsStudent> resultList = entityManager.createNativeQuery("Select * from Maths_Student where id='"+studentId+"'", MathsStudent.class).getResultList();
//        List<Student> resultList = entityManager.createQuery("Select s from Student s where s.id=:id", Student.class)
//                .setParameter("id", studentId).getResultList();
        return resultList;
    }

    @Override
    public Integer deleteStudent(String studentId) {
        Integer numberOfDeletedStudents = entityManager.createNativeQuery("Delete from Maths_Student where id='"+studentId+"'", MathsStudent.class).executeUpdate();
//        List<Student> resultList = entityManager.createQuery("Delete from Student where id=:id", Student.class)
//                .setParameter("id", studentId).getResultList();
        return numberOfDeletedStudents;
    }
}
