package com.tg.manager.view;

import java.util.Set;

import com.tg.manager.model.ToDoModel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NotasFeedbackScreen extends Application {
    private Set <ToDoModel> toDo;

    public NotasFeedbackScreen(Set <ToDoModel> toDo) {
        this.toDo = toDo;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NotasFeedback.fxml"));
        Parent root = fxmlLoader.load();
        Scene tela1 = new Scene(root);

        primaryStage.setScene(tela1);
        primaryStage.setResizable(false);
        primaryStage.show();

        System.out.println(toDo);
    }
}
