package com.tg.manager.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class LayoutRelatorioTecnicoController implements Initializable{

    @FXML
    private Label EmailInstitucional;

    @FXML
    private Label EmailPessoal;

    @FXML
    private Label Empresa;

    @FXML
    private Label NomeCompleto;

    @FXML
    private Button RelatorioEntrega;

    @FXML
    private Label RelatorioFeedback;

    @FXML
    private Button RelatorioGeral;

    @FXML
    private Label RelatorioNota;

    @FXML
    private Label TemaTg;

    @FXML
    private Label Turma;

    @FXML
    private ImageView botaoCalendar;

    @FXML
    private ImageView botaoClipboard;

    @FXML
    private ImageView botaoHome;

    public void initialize(URL url, ResourceBundle resourceBundle){

    }

    @FXML
    void goToDeliveryReport(ActionEvent event) {

    }

    @FXML
    void goToDeliveryScreen(MouseEvent event) {

    }

    @FXML
    void goToGeneralReport(ActionEvent event) {

    }

    @FXML
    void goToGeneralReportScreen(MouseEvent event) {

    }

    @FXML
    void goToHomeScreen(MouseEvent event) {

    }

}