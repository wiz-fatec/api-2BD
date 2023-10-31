package com.tg.manager.controller;

import com.tg.manager.model.AdvisorModel;
import java.util.ArrayList;
import java.util.List;

public class AdvisorController {
    public static void addAdivisor(List<List<String>> dataList){
        dataList.remove(0);
        for(List<String> data : dataList){
          String nameAdivisor = data.get(4).toUpperCase().trim();
          String emailAdivisor = data.get(5).toLowerCase().trim();

          if(!(AdvisorModel.AdvisorExist(emailAdivisor))){
              AdvisorModel.validatorAdvisor(nameAdivisor, emailAdivisor);
          }
        }
    }
}
