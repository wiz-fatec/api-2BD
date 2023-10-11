package com.tg.manager.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomeScreen extends Application {
  
  @Override
  public void start(Stage primaryStage) throws Exception {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HomeScreen.fxml"));
    Parent root = fxmlLoader.load();
    Scene tela = new Scene(root);

    primaryStage.setScene(tela);
    primaryStage.setResizable(false);
    primaryStage.show();
  }
}