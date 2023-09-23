package com.tg.manager.controller;

import java.util.ArrayList;

public class Student extends Person {
    String emailPersonal;

    public Student(String emailFatec, String name) {
        super(emailFatec, name);
    }
    public String getEmailPersonal() {
        return this.emailPersonal;
    }
    public void publishInDB() {

    }
    public Student getStudentByEmailFatec(String email) {
        return new Student("email", "name");
    }
    public Advisor getAdvisor() {
        return new Advisor();  
    }
    public static ArrayList<Student> getRange(Integer low, Integer high) {
        return new ArrayList<>();
    }
}
