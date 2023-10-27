package com.tg.manager.controller;

import com.tg.manager.model.TGModel;
import com.tg.manager.utils.EmailValidator;

import java.util.List;

public class TGController {
    public static void addATg(List<List<String>> dataList){
        for(List<String> data : dataList){
            String description = data.get(7).trim();
            String typeTg = data.get(6).toUpperCase().trim();
            String problem =data.get(8).trim();
            problem = problem.isEmpty() ? "" : problem;
            String enterprise = data.get(9).toUpperCase().trim();
            enterprise = enterprise.isEmpty() ? "" : enterprise;
            String discipline = data.get(10).trim();
            String emailFatecStudent = data.get(2).toLowerCase().trim();
            String emailStudent =data.get(1).toLowerCase().trim();
            String validatorFatecEmail = EmailValidator.validatorEmailFatec(emailFatecStudent, emailStudent);
            TGModel.validatorTG(description, typeTg, problem, enterprise, discipline, validatorFatecEmail);
        }
    }

}
