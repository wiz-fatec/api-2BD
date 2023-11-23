package com.tg.manager.view;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class GeneralReportAlert extends Application {

    @Override
    public void start(Stage outputStage) throws Exception {
        showInformationAlert();
    }

    public static void showInformationAlert() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Relatório");
        alert.setHeaderText(null);
        alert.setContentText("Relatório Gerado com Sucesso!");

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
