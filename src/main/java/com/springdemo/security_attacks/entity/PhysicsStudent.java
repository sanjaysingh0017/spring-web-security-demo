package com.springdemo.security_attacks.entity;

import jakarta.persistence.*;
import org.springframework.core.annotation.Order;

@Entity
@Table(name = "physics_student")
public class PhysicsStudent {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "marks")
    private Integer marks;
    @Column(name = "name")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMarks() {
        return marks;
    }

    public void setMarks(Integer marks) {
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
