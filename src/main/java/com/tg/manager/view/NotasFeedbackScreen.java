package com.tg.manager.view;

import java.util.Set;

import com.tg.manager.model.DisplayTableModel;
import com.tg.manager.model.SubmitModel;
import com.tg.manager.model.ToDoModel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NotasFeedbackScreen extends Application {
    public static Set <SubmitModel> toDo;

    public  static DisplayTableModel display;

    public NotasFeedbackScreen(){}

    public NotasFeedbackScreen(Set <SubmitModel> toDoArg, DisplayTableModel display) {
        this.display = display;
        toDo = toDoArg;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NotasFeedback.fxml"));
        Parent root = fxmlLoader.load();
        Scene tela1 = new Scene(root);

        primaryStage.setScene(tela1);
        primaryStage.setResizable(false);
        primaryStage.show();


    }
}
