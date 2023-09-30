package com.tg.manager.controller;

public class Person {
    String emailFatec;
    String name;

    public Person(String emailFatec, String name) {
        this.emailFatec = emailFatec;
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public String getEmailFatec() {
        return this.emailFatec;
    }
}
