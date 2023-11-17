package com.tg.manager;

import com.tg.manager.model.StudentModel;
import com.tg.manager.model.TGModel;
import com.tg.manager.model.ToDoModel;
import com.tg.manager.view.HomeScreen;
import com.tg.manager.view.HomeScreenWithTable;
import com.tg.manager.view.NotasFeedbackScreen;

import javafx.application.Application;


public class Main {
    public static void main(String[] args) {

    if (StudentModel.getSubmit().isEmpty()) {
        System.out.println("Hello world!");
        Application.launch(HomeScreen.class, args);
    } else {
        TGModel.getDisciplineTG(56);
        Application.launch(HomeScreenWithTable.class, args);
    }
    }
}