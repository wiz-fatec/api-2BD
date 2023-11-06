package com.tg.manager.controller;

import com.tg.manager.model.ToDoModel;
import com.tg.manager.model.SubmitModel;
import com.tg.manager.model.TeamModel;
import com.tg.manager.view.ControllerNotasFeedback;
import com.tg.manager.view.Entrega;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.text.SimpleDateFormat;
import java.util.Set;

public class ToDoController {

    public static void sendDataForDataBase(ToDoModel todomodel){

        Integer idIssue = todomodel.getIdIssue();
        Integer idStudent = todomodel.getIdStudent();
        Double nota = todomodel.getNote();
        String feedback = todomodel.getFeedback();
        
        ToDoModel.toDoValidator(idIssue, idStudent, nota, feedback);

    }

   public static ObservableList<ControllerNotasFeedback> getDataInDataBase(){
    Set<ToDoModel> listToDo = ToDoModel.getToDo();
      ObservableList<ControllerNotasFeedback> list = FXCollections.observableArrayList();
      for(ToDoModel todo : listToDo){
         String feedbackTd = 
         SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
         String initialDate = formatter.format( submit.getInitialDate());
         String finalDate = formatter.format( submit.getFinalDate());
         String typeTg = convertForTg(submit.getIdTeam());
         Entrega entrega = new Entrega(description, typeTg, initialDate, finalDate);
         list.add(entrega);
      }
      return list;
   }
    
    
}
