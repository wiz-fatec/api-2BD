package com.tg.manager.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LayoutRelatorioDisciplinaController implements Initializable{

    @FXML
    private Label Disciplina;

    @FXML
    private Label EmailInstitucional;

    @FXML
    private Label EmailPessoal;

    @FXML
    private Label NomeCompleto;

    @FXML
    private Label TemaTg;

    @FXML
    private Button RelatorioEntrega;

    @FXML
    private Label RelatorioFeedback;

    @FXML
    private Button RelatorioGeral;

    @FXML
    private Label RelatorioNota;

    @FXML
    private Label Turma;

    @FXML
    private ImageView botaoCalendar;

    @FXML
    private ImageView botaoClipboard;

    @FXML
    private ImageView botaoHome;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        EmailInstitucional.setText(ButtonCell.getDisplayModel1().getStudent().getFatecEmail());
        NomeCompleto.setText(ButtonCell.getDisplayModel1().getStudent().getName());
        EmailPessoal.setText(ButtonCell.getDisplayModel1().getStudent().getEmail());
        //Turma.setText(ButtonCell.getDisplayModel1().getStudent().getTypeTg());
    }


    @FXML
    void goToDeliveryReport(ActionEvent event) {

    }

    @FXML
    void goToDeliveryScreen(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("NovaEntregaScreen.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) botaoCalendar.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }    

    }

    @FXML
    void goToGeneralReport(ActionEvent event) {

    }

    @FXML
    void goToGeneralReportScreen(MouseEvent event) {

    }

    @FXML
    void goToHomeScreen(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeScreenWithTable.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) botaoCalendar.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        }

}
