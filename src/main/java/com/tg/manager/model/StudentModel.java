package com.tg.manager.model;

public class StudentModel {

    private String student;
    private String institutionalEmail;

    public StudentModel(String student, String institutionalEmail) {
        this.student = student;
        this.institutionalEmail = institutionalEmail;
    }

    public String getStudent() {
        return student;
    }

    public String getInstitutionalEmail() {
        return institutionalEmail;
    }
}