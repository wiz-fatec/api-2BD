package com.tg.manager.controller;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.tg.manager.model.StudentModel;
import com.tg.manager.model.TGModel;
import com.tg.manager.model.TeamModel;
import com.tg.manager.model.AdvisorModel;
import java.util.Set;

public class CSVHandler {

    public static void populateDataBase(List<List<String>> dataList) {
        System.out.println(dataList);

        AdvisorModel advisorModel = new AdvisorModel();
        StudentModel studentModel = new StudentModel();
        
        TGModel tgModel = new TGModel();
        TeamModel teamModel = new TeamModel();
        teamModel.addTeam(1, 2020);
        Integer teamId = 0;
        try {
            Set<TeamModel> setTeamModel = teamModel.getSubmit();
            for(TeamModel tm: setTeamModel) {
                teamId = tm.getId();
                break;
            }
        } catch(Exception e) {}

        for (int i = 1; i < dataList.size(); i++) {
            
            try {
                List<String> subList = dataList.get(i);
                System.out.println("Advisor: "+subList.get(4)+subList.get(5));
                advisorModel.addAdvisor(subList.get(4),subList.get(5));
                System.out.println("Student: "+subList.get(3)+subList.get(1)+subList.get(2));
                try {
                    Set<AdvisorModel> setAdvisorModel = advisorModel.getSubmit();
                    for (AdvisorModel am: setAdvisorModel) {
                        if(am.getFatecEmail().toLowerCase().equals(subList.get(5).toLowerCase())) {
                            studentModel.addStudent(subList.get(3), subList.get(1), subList.get(2),am.getId(),teamId);
                            break;
                        }
                    }
                } catch(Exception e ) {}
                System.out.println("TG: "+subList.get(7)+ subList.get(8)+subList.get(9)+subList.get(10));
                try {
                    Set<StudentModel> setStudentModel = studentModel.getSubmit();
                    for (StudentModel sm: setStudentModel) {
                        System.out.println(sm.getFatecEmail().toLowerCase());
                        System.out.println(subList.get(2).toLowerCase());
                        if(sm.getFatecEmail().toLowerCase().equals(subList.get(2).toLowerCase())) {
                            tgModel.addTG("", subList.get(7), subList.get(8), subList.get(9), subList.get(10), sm.getId());
                            break;
                        }
                    }
                } catch(Exception e) {}
            } catch(Error e) {}
        }
    }
    //public static void main(String[] args) {
    //    List<List<String>> dataList = new ArrayList<>(Arrays.asList(
    //            Arrays.asList("Timestamp", "Email Address", "Email Fatec (se diferente do informado anteriormente)", "Nome completo:", "Nome completo do professor orientador:", "Email Fatec do professor orientador:", "Matriculado em:", "Tipo de TG:", "Problema a ser resolvido/estudado no artigo", "Empresa envolvida:", "Disciplina:"),
    //            Arrays.asList("8/15/2023 9:06:49", "V4eylW1rBk@gmail.com", "qqkaYfzRDZ@fatec.sp.gov.br", "M...aga", "Gerson Da Penha Neto", "gerson.penha@fatec.sp.gov.br", "TG2", "Portfólio (Exige participação em todos os 6 APIs)", "", "", ""),
    //            Arrays.asList("8/25/2023 22:13:52", "1GnjryyjH1@gmail.com", "B0Hre3EoQA@fatec.sp.gov.br", "M...lva", "Jean Carlos Lourença Costa", "jean.costa4@fatec.sp.gov.br", "TG1", "Relatório Técnico - Estágio (Somente para quem não pode participar de 6 APIs. Autorizado pela empresa)", "", "Dnu3Qtp", "")
    //    ));
    //    
    //    CSVHandler.populateDataBase(dataList);
    //}
}