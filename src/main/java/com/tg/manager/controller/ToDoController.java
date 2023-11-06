package com.tg.manager.controller;

import com.tg.manager.model.*;
import com.tg.manager.view.ControllerNotasFeedback;
import com.tg.manager.view.Entrega;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.text.SimpleDateFormat;
import java.util.Set;

public class ToDoController {

    public static void sendDataForDataBase(String feedback, String note, String dataSubmit, DisplayTableModel dataStudent){
          Double  noteSubmit = Double.parseDouble(note);
          String feedbackSubmit = feedback;
          Integer idIssueSubmit = getIdDataSubmit(dataSubmit);
          Integer idStudentSubmit = dataStudent.getStudent().getId();
          ToDoModel.addToDo(feedbackSubmit,noteSubmit, idStudentSubmit, idIssueSubmit);

    }

    private static int getIdDataSubmit(String dataSubmit){
        for(SubmitModel data : SubmitModel.getSubmit()){
            if(data.getDescription().equals(dataSubmit)){
                return data.getId();
            }
        }
        throw new RuntimeException("Submit not exist");

    }


}