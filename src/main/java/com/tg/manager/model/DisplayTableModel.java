package com.tg.manager.model;

import lombok.Data;
import lombok.ToString;
import java.util.HashSet;
import java.util.Set;

@Data
@ToString
public class DisplayTableModel {
   private StudentModel student;

   private Set<ToDoModel> valuesFeedbacks;

   private boolean apt;

   private String report;

    public static Set<DisplayTableModel> getDataTable(){
        Set<DisplayTableModel> listDataTable = new HashSet<>();
        Set<StudentModel> listStudent = StudentModel.getSubmit();
        for(StudentModel studentData : listStudent){
            DisplayTableModel dataTable = new DisplayTableModel();
            dataTable.setStudent(studentData);
            dataTable.setValuesFeedbacks(getNoteAndFeedback(studentData.getId()));
            dataTable.setApt(isApt(studentData.getTeamId(), studentData.getId()));
            dataTable.setReport("Relatorinho");
            listDataTable.add(dataTable);
        }
        return listDataTable;
    }

    private static Set<ToDoModel> getNoteAndFeedback(Integer idStudent){
        Set<ToDoModel> listToDoModel = ToDoModel.getToDo();
        Set<ToDoModel> listExistent = new HashSet<>();
        for(ToDoModel dataToDo : listToDoModel){
            if(dataToDo.getIdStudent().equals(idStudent)){
                listExistent.add(dataToDo);
            }
        }
        return listExistent;

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
}
