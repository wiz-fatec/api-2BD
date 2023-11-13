package com.tg.manager.model;

import com.tg.manager.utils.ModelTGEnum;
import lombok.Data;
import lombok.ToString;
import java.util.HashSet;
import java.util.Set;

@Data
@ToString
public class DisplayTableModel {
   private StudentModel student;

   private Set<SubmitModel> valuesFeedbacks;

   private boolean apt;

   private String report;

   private String typeTg;

    public static Set<DisplayTableModel> getDataTable(){
        Set<DisplayTableModel> listDataTable = new HashSet<>();
        Set<StudentModel> listStudent = StudentModel.getSubmit();
        for(StudentModel studentData : listStudent){
            DisplayTableModel dataTable = new DisplayTableModel();
            String descriptionTg = TGModel.getModelTg(studentData.getId());
            String descriptionTgEnum = descriptionTg(descriptionTg);
            String typeTg = typeTG(studentData.getId());
            dataTable.setStudent(studentData);
            dataTable.setValuesFeedbacks(getNoteAndFeedback(studentData.getTeamId(), descriptionTg));
            dataTable.setApt(isApt(studentData.getTeamId(), studentData.getId()));
            dataTable.setReport("Relatorinho");
            dataTable.setTypeTg(typeTg + " - "+ descriptionTgEnum);
            listDataTable.add(dataTable);
        }
        return listDataTable;
    }

    public static Set<SubmitModel> getNoteAndFeedback(Integer idTeamStudent, String descriptionTG){
        Set<SubmitModel> listSubmitExistent = new HashSet<>();
        for(SubmitModel dataSubmit :SubmitModel.getSubmit()){
            String validatorTypeTg = validatorEnumTypeTg(dataSubmit.getModel());
            if(descriptionTG.equals(validatorTypeTg)) {
                if (isTG1andTG2(idTeamStudent)) {
                    listSubmitExistent.add(dataSubmit);
                } else {
                    if (dataSubmit.getIdTeam().equals(idTeamStudent)) {
                        listSubmitExistent.add(dataSubmit);
                    }
                }
            }
        }
        return listSubmitExistent;

    }

    private static boolean isApt(Integer idTeam, Integer idStudent) {
        Integer quantityTodoStudent = quantitySubmit(idTeam);
        Integer quantitySubmitStudent = quantityTodo(idStudent);
        if (quantitySubmitStudent != 0 && (quantityTodoStudent / quantitySubmitStudent) == 1) {
            return true;
        }
        return false;
    }

    private static Integer quantityTodo(Integer idStudent){
        Integer occurrences = (int) ToDoModel.getToDo().stream()
                .filter(e -> e.getIdStudent().equals(idStudent))
                .count();
        return occurrences;

    }

    private static Integer quantitySubmit(Integer idTeam){
        Integer occurrences = (int) SubmitModel.getSubmit().stream()
                .filter(e -> e.getIdTeam().equals(idTeam))
                .count();
        return occurrences;
    }

    private static boolean isTG1andTG2(Integer id ){
        for(TeamModel team : TeamModel.getSubmit()){
            if(team.getId().equals(id)){
                boolean isTG1TG2 = team.getSemester().equals(3) ? true : false;
                return isTG1TG2;
            }
        }
       return false;
    }

    private static String validatorEnumTypeTg(String descriptionTg){

        if(descriptionTg.equals("Estágio - Técnico")){
            return ModelTGEnum.INTERNSHIP.getDescription();
        } else if (descriptionTg.equals("Técnico - Disciplina")) {
            return ModelTGEnum.DISCIPLINE.getDescription();
        } else if (descriptionTg.equals("Científico")) {
            return ModelTGEnum.ARTICLE.getDescription();
        } else if (descriptionTg.equals("Portfólio")) {
            return ModelTGEnum.PORTIFOLIO.getDescription();
        }
        return null;

    }

    public static String descriptionTg(String description){
        for( ModelTGEnum x : ModelTGEnum.values()){
            if(x.getDescription().equals(description)){
                return translateTg(x.name());
            }
        }
        return null;
    }

    private static String typeTG(int idStudent){
       return TGModel.getTypeTg(idStudent);
    }


    private static String translateTg(String description){
        switch (description){
            case "INTERNSHIP":
                return "Estágio - Técnico";
            case "DISCIPLINE":
                return "Técnico - Disciplina";
            case "ARTICLE":
                return "Científico";
            case "PORTIFOLIO":
                return "Portfólio";
            default:
                throw new RuntimeException("Description not exist");
        }

    }
}
