package com.tg.manager.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NotasFeedbackScreen extends Application {
  
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
