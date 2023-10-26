package com.tg.manager.controller;

import com.tg.manager.model.AdvisorModel;
import java.util.ArrayList;
import java.util.List;

public class AdvisorController {
    private static List<String> listAdvisor = new ArrayList<>();

    public static void addAdivisor(List<List<String>> dataList){
        dataList.remove(0);
        for(List<String> data : dataList){
          String nameAdivisor = data.get(4).toUpperCase().trim();
          String emailAdivisor = data.get(5).toLowerCase().trim();
          if(!(listAdvisor.contains(emailAdivisor))){
              listAdvisor.add(emailAdivisor);
              AdvisorModel.validatorAdvisorEmail(nameAdivisor, emailAdivisor);
          }
        }
    }
}
