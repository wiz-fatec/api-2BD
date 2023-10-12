package com.tg.manager;


import com.tg.manager.view.HomeScreen;
import com.tg.manager.view.HomeScreenWithTable;

import javafx.application.Application;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");


        Application.launch(HomeScreenWithTable.class, args);
    }
}