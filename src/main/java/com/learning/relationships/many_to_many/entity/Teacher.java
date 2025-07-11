package com.learning.relationships.many_to_many.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

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

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "teacher_uni"
        , joinColumns = @JoinColumn(name = "teacher_id")
        , inverseJoinColumns = @JoinColumn(name = "university_id")
    )
    private List<University> universities = new ArrayList<>();

    public void addUniversityToTeacher(University university){
        universities.add(university);
    }

    public Teacher() {
    }

    public Teacher(String name, String surname, String subject, Boolean isProfessor) {
        this.name = name;
        this.surname = surname;
        this.subject = subject;
        this.isProfessor = isProfessor;
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

    public List<University> getUniversities() {
        return universities;
    }

    public void setUniversities(List<University> universities) {
        this.universities = universities;
    }

    @Override
    public String toString() {
        return "Teacher [id=" + id + ", name=" + name + ", surname=" + surname + ", subject=" + subject
                + ", isProfessor=" + isProfessor + "]";
    }

}
