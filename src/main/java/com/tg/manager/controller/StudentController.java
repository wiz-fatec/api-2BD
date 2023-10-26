package com.tg.manager.controller;

import com.tg.manager.model.StudentModel;
import java.util.List;

public class StudentController {
    public static void addAStudent(List<List<String>> dataList){
        for(List<String> data : dataList){
            String nameStudent = data.get(3).toUpperCase();
            String emailFatecStudent = data.get(2).toLowerCase().trim();
            String emailStudent =data.get(1).toLowerCase().trim();
            String emailAdvisor = data.get(5).toLowerCase().trim();
            String typeTg = data.get(6);
            if(emailFatecStudent.isEmpty()){
                emailFatecStudent = emailStudent;
            }
            StudentModel.validator(emailStudent, emailFatecStudent, nameStudent, emailAdvisor, typeTg );
        }
    }
}
