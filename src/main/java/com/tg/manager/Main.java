package com.tg.manager;


import com.tg.manager.model.TeamModel;
import com.tg.manager.view.HomeScreen;

import javafx.application.Application;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        TeamModel turma = new TeamModel();
//        turma.addTeam(6, 2024);
//        turma.addTeam(7, 2023);
        try {
            System.out.println(turma.getSubmit());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Application.launch(HomeScreen.class, args);
    }
}