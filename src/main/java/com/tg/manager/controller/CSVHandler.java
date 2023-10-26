package com.tg.manager.controller;
import java.util.*;
import com.tg.manager.model.StudentModel;
import com.tg.manager.model.TGModel;



public class CSVHandler {

    public static void populateDataBase(List<List<String>> dataList) {

        TGModel tgModel = new TGModel();
        int c =0;
        TeamController.addTeam();
        AdvisorController.addAdivisor(dataList);
        StudentController.addAStudent(dataList);


    }

}