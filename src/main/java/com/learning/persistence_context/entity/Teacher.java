package com.learning.persistence_context.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Cacheable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

// @Cacheable
// @Entity
// @Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "subject")
    private String subject;

    @Column(name = "is_professor")
    private Boolean isProfessor;

    public Teacher() {
    }

    public Teacher(String name, String surname, String subject, Boolean isProfessor) {
        this.name = name;
        this.surname = surname;
        this.subject = subject;
        this.isProfessor = isProfessor;
    }

    @PrePersist
    void prePersist(){
        System.out.println("@PrePersist");
    }

    @PostPersist
    void postPersist(){
        System.out.println("@PostPersist");
    }

    @PreUpdate
    void preUpdate(){
        System.out.println("@PreUpdate");
    }

    @PostUpdate
    void postUpdate(){
        System.out.println("@PostUpdate");
    }

    @PreRemove
    void preRemove(){
        System.out.println("@PreRemove");
    }

    @PostRemove
    void postRemove(){
        System.out.println("@PostRemove");
    }

    @PostLoad
    void postLoad(){
        System.out.println("@PostLoad");
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Boolean getIsProfessor() {
        return isProfessor;
    }

    public void setIsProfessor(Boolean isProfessor) {
        this.isProfessor = isProfessor;
    }

    @Override
    public String toString() {
        return "Teacher [id=" + id + ", name=" + name + ", surname=" + surname + ", subject=" + subject
                + ", isProfessor=" + isProfessor + "]";
    }

}
