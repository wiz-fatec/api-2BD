package com.tg.manager.model;

import com.tg.manager.utils.ModelTGEnum;
import com.tg.manager.utils.ReportPdf;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import lombok.var;

import java.util.HashSet;
import java.util.Set;

@Data
@ToString
public class DisplayTableModel {
   private StudentModel student;

   private Set<SubmitModel> valuesFeedbacks;

   @Getter
   private boolean apt;

   private String report;

   private String typeTg;

   private String feedbackTG;

   private  String noteTG;

   private  String enterpriseTG;

   private  String problemTG;

   private  String disciplineTG;

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

    public static boolean isApt(Integer idTeam, Integer idStudent) {
        Integer quantityTodoStudent = quantitySubmit(idTeam);
        Integer quantitySubmitStudent = quantityTodo(idStudent);
        if(quantityTodoStudent != null && quantitySubmitStudent != null) {
            var calcIsApt = quantityTodoStudent / quantitySubmitStudent;
            return calcIsApt == 1;
        }
        return false;
    }

    private static Integer quantityTodo(Integer idStudent){
        Set<ToDoModel> todoList = ToDoModel.filterTodo(idStudent);
        if(!todoList.isEmpty()){
            return todoList.size();
        }
        return null;

    }

    private static Integer quantitySubmit(Integer idTeam){
        Set<SubmitModel> submitList = SubmitModel.filterSubmit(idTeam);
        if(!submitList.isEmpty()) {
            return submitList.size();
        }
        return null;
    }

    private static boolean isTG1andTG2(Integer id ){
        for(TeamModel team : TeamModel.getSubmit()){
            if(team.getId().equals(id)){
                boolean isTG1TG2 = team.getSemester().equals(3);
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
        for( ModelTGEnum nameEnum : ModelTGEnum.values()){
            if(nameEnum.getDescription().equals(description)){
                return translateTg(nameEnum.name());
            }
        }
        return null;
    }

    private static String typeTG(int idStudent){
       return TGModel.getTypeTg(idStudent);
    }
    private static String feedbackTG(int idStudent){
       return ToDoModel.getFeedBackToDo(idStudent);
    }
    private static String noteTG(int idStudent){
       return ToDoModel.getNoteToDo(idStudent);
    }
    private static String enterpriseTG(int idStudent){
       return TGModel.getEnterpriseTG(idStudent);
    }
    private static String problemTG(int idStudent){
       return TGModel.getProblemTG(idStudent);
    }
    private static String disciplineTG(int idStudent){
       return TGModel.getDisciplineTG(idStudent);
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

    public static Set<DisplayTableModel> filterTable(String descriptionTg){
        if(descriptionTg != null) {
            Set<DisplayTableModel> listStudent = new HashSet<>();
            for (DisplayTableModel find : DisplayTableModel.getDataTable()) {
                if(find.getTypeTg().contains(descriptionTg)){
                    listStudent.add(find);
                }
            }
            return listStudent;
        }
        return DisplayTableModel.getDataTable();
    }

    public boolean getIsApt(){
        return this.apt;

    }

    public static void reportIsApt(){
        ReportPdf.reportIsAptPdf(DisplayTableModel.getDataTable());
    }

}
