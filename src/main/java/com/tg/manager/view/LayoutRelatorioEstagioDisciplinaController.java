package com.tg.manager.view;

import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;

import com.tg.manager.model.AdvisorModel;
import com.tg.manager.model.StudentModel;
import com.tg.manager.model.TGModel;
import com.tg.manager.model.ToDoModel;

import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LayoutRelatorioEstagioDisciplinaController implements Initializable{

    @FXML
    private Label EmailInstitucional;

    @FXML
    private Label EmailPessoal;

    @FXML
    private Label NomeCompleto;

    @FXML
    private Label Orientador;

    @FXML
    private Label EmailOrientador;

    @FXML
    private Button RelatorioEntrega;

    @FXML
    private Label RelatorioFeedback;

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

    @FXML
    private Label disciplinaLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EmailInstitucional.setText("E-mail Fatec: "+ButtonCell.getDisplayModel1().getStudent().getFatecEmail());
        NomeCompleto.setText("Nome: "+ButtonCell.getDisplayModel1().getStudent().getName());
        EmailPessoal.setText("E-mail: "+ButtonCell.getDisplayModel1().getStudent().getEmail());
        Turma.setText("Turma: "+ButtonCell.getDisplayModel1().getTypeTg());
        if (ToDoModel.getNoteToDo(ButtonCell.getDisplayModel1().getStudent().getId())!= null){
            RelatorioNota.setText(ToDoModel.getNoteToDo(ButtonCell.getDisplayModel1().getStudent().getId()));
        }
        if (ToDoModel.getFeedBackToDo(ButtonCell.getDisplayModel1().getStudent().getId())!= null){
            RelatorioFeedback.setText(ToDoModel.getFeedBackToDo(ButtonCell.getDisplayModel1().getStudent().getId()));;
        }
        Orientador.setText("Orientador: " + AdvisorModel.filterIdAdvisor(ButtonCell.getDisplayModel1().getStudent().getAdvisorId()).getName());
        EmailOrientador.setText("E-mail orientador: " + AdvisorModel.filterIdAdvisor(ButtonCell.getDisplayModel1().getStudent().getAdvisorId()).getFatecEmail());
        disciplinaLabel.setText("Disciplina: "+TGModel.getDisciplineTG(ButtonCell.getDisplayModel1().getStudent().getId()));
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
    void goToGeneralReportScreen(MouseEvent event) {
        StudentModel.getReport();
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

