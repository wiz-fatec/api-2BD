package com.tg.manager.view;

import com.tg.manager.model.DisplayTableModel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PerfilAlunoScreen extends Application{
    private static DisplayTableModel display2;

    public PerfilAlunoScreen(DisplayTableModel display2) {
        this.display2 = display2;
    }
        @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PerfilAlunoScreen.fxml")); 
        Parent root = fxmlLoader.load();
        Scene tela1 = new Scene(root);

        primaryStage.setScene(tela1);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
