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
            if(dataToDo.getIdStudent() == idStudent){
                listExistent.add(dataToDo);
            }
        }
        return listExistent;

    }

    private static boolean isApt(Integer idTeam, Integer idStudent){
        Integer quantityTodoStudent = quantitySubmit(idTeam);
        Integer quantitySubmitStudent = quantityTodo(idStudent);
        if((quantityTodoStudent / quantitySubmitStudent) == 1){
            return true;
        }
        return false;

    }

    private static Integer quantityTodo(Integer idStudent){
        Set<ToDoModel> toDoList = ToDoModel.getToDo();
        Integer count = 0;
        for( ToDoModel toDo : toDoList){
            if(toDo.getIdStudent() == idStudent){
                count++;
            }
        }
        return  count;

    }

    private static Integer quantitySubmit(Integer idTeam){
        Set<SubmitModel> listSubmit = SubmitModel.getSubmit();
        Integer count = 0;
        for( SubmitModel toDo : listSubmit){
            if(toDo.getIdTeam() == idTeam){
                count++;
            }
        }
        return  count;

    }
}
