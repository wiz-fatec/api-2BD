package com.tg.manager.controller;
import java.util.*;



public class CSVHandler {

    public static void populateDataBase(List<List<String>> dataList) {

        AdvisorController.addAdivisor(dataList);
        TeamController.addTeam();
        StudentController.addAStudent(dataList);
        TGController.addATg(dataList);
    }

}