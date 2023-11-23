package com.tg.manager.view;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class GradeAndFeedbackAlert extends Application {

    @Override
    public void start(Stage outputStage) throws Exception {
        showWarningAlert();
        showCommentWarningAlert();
    }

    public static void showWarningAlert() {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Atenção");
        alert.setHeaderText(null);
        alert.setContentText("Notas não devem ser negativas e maiores que dez");

        alert.getDialogPane().setStyle(
                "-fx-background-color: #303236; " +
                        "-fx-text-fill: white; " +
                        "-fx-control-inner-background: #303236;"
        );

        alert.getDialogPane().lookupButton(alert.getButtonTypes().get(0)).setStyle(
                "-fx-base: #393939; " +
                        "-fx-text-fill: white;"
        );

        alert.getDialogPane().lookup(".content.label").setStyle("-fx-text-fill: white;");

        alert.showAndWait();
    }

    public static void showCommentWarningAlert() {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Atenção");
        alert.setHeaderText(null);
        alert.setContentText("Por favor, insira um feedback!");

        alert.getDialogPane().setStyle(
                "-fx-background-color: #303236; " +
                        "-fx-text-fill: white; " +
                        "-fx-control-inner-background: #303236;"
        );

        alert.getDialogPane().lookupButton(alert.getButtonTypes().get(0)).setStyle(
                "-fx-base: #393939; " +
                        "-fx-text-fill: white;"
        );

        alert.getDialogPane().lookup(".content.label").setStyle("-fx-text-fill: white;");

        alert.showAndWait();
    }
}