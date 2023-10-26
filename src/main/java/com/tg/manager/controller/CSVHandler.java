package com.tg.manager.controller;
import java.util.*;
import com.tg.manager.model.StudentModel;
import com.tg.manager.model.TGModel;



public class CSVHandler {

    public static void populateDataBase(List<List<String>> dataList) {

        AdvisorController.addAdivisor(dataList);
        TeamController.addTeam();
        StudentController.addAStudent(dataList);


    }

}