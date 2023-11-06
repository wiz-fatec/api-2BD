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
    private Set <SubmitModel> toDo;
    private DisplayTableModel display;

    public NotasFeedbackScreen(Set <SubmitModel> toDo, DisplayTableModel display) {
        this.toDo = toDo;
        this.display = display;
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
//        System.out.println(display);
    }
}
