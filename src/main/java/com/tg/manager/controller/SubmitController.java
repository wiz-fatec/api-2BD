package com.tg.manager.controller;

import com.tg.manager.model.SubmitModel;
import com.tg.manager.model.TeamModel;
import com.tg.manager.model.ToDoModel;
import com.tg.manager.view.Entrega;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.text.SimpleDateFormat;
import java.util.Set;

public class SubmitController {

   public static void setDataInDataBase(Entrega data) {
      String description = data.getAtividade();
      String typeTg = data.getTipoTG();
      String initialDate = data.getDataInicial();
      String finalDate = data.getDataFinal();
      String model = data.getTgModelo();
      SubmitModel.submitValidator(description, initialDate, finalDate, typeTg, model);
   }

   public static ObservableList<Entrega> getDataInDataBase() {
      Set<SubmitModel> listSubmit = SubmitModel.getSubmit();
      ObservableList<Entrega> list = FXCollections.observableArrayList();
      for (SubmitModel submit : listSubmit) {
         Integer id = submit.getId();
         String description = submit.getDescription();
         SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
         String initialDate = formatter.format(submit.getInitialDate());
         String finalDate = formatter.format(submit.getFinalDate());
         String typeTg = convertForTg(submit.getIdTeam());
         String model = submit.getModel();
         Entrega entrega = new Entrega(id, description, typeTg, initialDate, finalDate, model);
         list.add(entrega);
      }
      return list;
   }

   public static String convertForTg(int idTeam) {
      for (TeamModel team : TeamModel.getSubmit()) {
         if (team.getId().equals(idTeam)) {
            String typeTg = team.getSemester().equals(1) ? "TG1" : "TG2";
            return typeTg;
         }
      }
      throw new RuntimeException("Id Team not exist");

   }
}
