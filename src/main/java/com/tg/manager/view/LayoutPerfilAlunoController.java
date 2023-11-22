package com.tg.manager.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.tg.manager.model.AdvisorModel;
import com.tg.manager.model.TGModel;

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
    private Label EmailPessoal;

    @FXML
    private Label NomeCompleto;

    @FXML
    private Label Orientador1;

    @FXML
    private Label Complemento;

    @FXML
    private Label Turma1;

    @FXML
    private ImageView botaoCalendar;

    @FXML
    private ImageView botaoClipboard;

    @FXML
    private ImageView botaoHome;

    @FXML
    
    public void initialize(URL url, ResourceBundle resourceBundle) {
        EmailInstitucional.setText("E-mail Fatec: "+ButtonCell.getDisplayModel2().getStudent().getFatecEmail());
        NomeCompleto.setText("Nome: " + ButtonCell.getDisplayModel2().getStudent().getName());
        EmailPessoal.setText("E-mail: "+ButtonCell.getDisplayModel2().getStudent().getEmail());
        Turma1.setText("Turma: "+ButtonCell.getDisplayModel2().getTypeTg());
        Orientador1.setText("Orientador: " + AdvisorModel.filterIdAdvisor(ButtonCell.getDisplayModel2().getStudent().getAdvisorId()).getName());
        EmailOrientador1.setText("E-mail orientador: " + AdvisorModel.filterIdAdvisor(ButtonCell.getDisplayModel2().getStudent().getAdvisorId()).getFatecEmail());
        
        String infoTg = ButtonCell.getDisplayModel2().getTypeTg();

        if (infoTg.contains("Científico")){
            Complemento.setText("Problema: " +TGModel.getProblemTG(ButtonCell.getDisplayModel2().getStudent().getId()));
        }
        if (infoTg.contains("Estágio")){
            Complemento.setText("Empresa: " +TGModel.getEnterpriseTG(ButtonCell.getDisplayModel2().getStudent().getId()));
        }
        
        if(infoTg.contains("Disciplina")){
            Complemento.setText("Disciplina: " +TGModel.getDisciplineTG(ButtonCell.getDisplayModel2().getStudent().getId()));
        }

        if(infoTg.contains("Portfólio")){
            Complemento.setText(null);
        }

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
