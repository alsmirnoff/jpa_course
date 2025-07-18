package com.learning.advanced_mapping.entity;

import jakarta.persistence.Embeddable;

// @Embeddable
public class Friend {

    private String name;
    private String surname;
    private int age;

    public Friend() {
    }

    public Friend(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Friend [name=" + name + ", surname=" + surname + ", age=" + age + "]";
    }

}
