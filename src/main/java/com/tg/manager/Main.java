package com.tg.manager;

import com.tg.manager.model.connection.ConnectionDataBase;
import com.tg.manager.view.HomeScreen;

import javafx.application.Application;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        ConnectionDataBase conect = new ConnectionDataBase();
        conect.getConexao();

        Application.launch(HomeScreen.class, args);
    }
}