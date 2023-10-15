package com.tg.manager.view;

public class StudentMock {

    private String student;
    private String institutionalEmail;

    public StudentMock(String student, String institutionalEmail) {
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
