package com.learning.jpql.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "universities")
// @NamedQuery(name = "University.allUniversitiesLessOrEqualTo2", query = "SELECT u FROM University u WHERE size(u.students) <= 2")
// @NamedQuery(name = "University.studentsWithAvgGradeBetween", query = "SELECT s FROM Student s WHERE avgGrade BETWEEN :from AND :to")
@NamedQueries({
    @NamedQuery(name = "University.allUniversitiesLessOrEqualTo2", query = "SELECT u FROM University u WHERE size(u.students) <= 2"),
    @NamedQuery(name = "University.studentsWithAvgGradeBetween", query = "SELECT s FROM Student s WHERE avgGrade BETWEEN :from AND :to")
})
public class University {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "founding_date")
    private Date foundingDate;

    @OneToMany(mappedBy = "university")
    private List<Student> students = new ArrayList<>();

    public void addStudentToUniversity(Student student){
        students.add(student);
        student.setUniversity(this);
    }

    public University() {
    }

    public University(String name, Date foundingDate) {
        this.name = name;
        this.foundingDate = foundingDate;
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

    public Date getFoundingDate() {
        return foundingDate;
    }

    public void setFoundingDate(Date foundingDate) {
        this.foundingDate = foundingDate;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "University [id=" + id + ", name=" + name + ", foundingDate=" + foundingDate + "]";
    }

}
