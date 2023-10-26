package com.tg.manager.controller;

import com.tg.manager.model.StudentModel;
import java.util.List;

public class StudentController {
    public static void addAStudent(List<List<String>> dataList){
        dataList.remove(0);
        for(List<String> data : dataList){
            String nameStudent = data.get(3).toUpperCase();
            String emailFatecStudent = data.get(2).toLowerCase().trim();
            String emailStudent =data.get(1).toLowerCase().trim();
            String nameAdvisor = data.get(4).toUpperCase().trim();
            String typeTg = data.get(6);
            if(emailFatecStudent.isEmpty()){
                emailFatecStudent = emailStudent;
            }
            StudentModel.validator(emailStudent, emailFatecStudent, nameStudent, nameAdvisor, typeTg );
        }
    }
}
