package com.tg.manager;

import com.tg.manager.model.StudentModel;
import com.tg.manager.view.HomeScreen;
import com.tg.manager.view.HomeScreenWithTable;

import javafx.application.Application;


public class Main {
    public static void main(String[] args) {

    if (StudentModel.getSubmit().isEmpty()) {
        System.out.println("Hello world!");
        Application.launch(HomeScreen.class, args);
    } else {
        Application.launch(HomeScreenWithTable.class, args);
    }
    }
}