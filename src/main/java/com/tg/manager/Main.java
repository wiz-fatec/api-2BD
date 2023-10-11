package com.tg.manager;

import com.tg.manager.model.AdvisorModel;
import com.tg.manager.model.StudentModel;
import com.tg.manager.model.TGModel;
import com.tg.manager.model.connection.ConnectionDataBase;

public class Main {
    public static void main(String[] args) {

        AdvisorModel advisor = new AdvisorModel();
        advisor.addAdvisor("Otavio", "otavio@teste.com.br");
    }
}