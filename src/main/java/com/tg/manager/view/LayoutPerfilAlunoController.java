package com.tg.manager.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LayoutPerfilAlunoController implements Initializable{

    @FXML
    private Label EmailInstitucional;

    @FXML
    private Label EmailOrientador1;

    @FXML
    private Label EmailOrientador2;

    @FXML
    private Label EmailPessoal;

    @FXML
    private Label ModeloTG1;

    @FXML
    private Label ModeloTG2;

    @FXML
    private Label NomeCompleto;

    @FXML
    private Label Orientador1;

    @FXML
    private Label Orientador2;

    @FXML
    private Label TemaTG1;

    @FXML
    private Label TemaTG2;

    @FXML
    private Label Turma1;

    @FXML
    private Label Turma2;

    @FXML
    private ImageView botaoCalendar;

    @FXML
    private ImageView botaoClipboard;

    @FXML
    private ImageView botaoHome;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        NomeCompleto.setText(ButtonCell.getDisplayModel2().getStudent().getName());
        EmailPessoal.setText(ButtonCell.getDisplayModel2().getStudent().getEmail());
        EmailInstitucional.setText(ButtonCell.getDisplayModel2().getStudent().getFatecEmail());

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
