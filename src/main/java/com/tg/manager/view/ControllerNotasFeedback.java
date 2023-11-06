package com.tg.manager.view;


import java.io.IOException;
import java.net.URL;
import java.util.*;

import javax.crypto.spec.RC2ParameterSpec;

import com.tg.manager.controller.SubmitController;
import com.tg.manager.controller.ToDoController;
import com.tg.manager.model.SubmitModel;
import com.tg.manager.model.ToDoModel;
import com.tg.manager.view.NotasFeedbackScreen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControllerNotasFeedback implements Initializable {


    @FXML
    private Button Botao;

    @FXML
    private TextField Feedback;

    @FXML
    private TextField Nota;

    @FXML
    private ImageView botaoCalendar;

    @FXML
    private ImageView botaoClipboard;

    @FXML
    private ImageView botaoHome;

    @FXML
    private ChoiceBox<String> escolhaDeEntrega;

    @FXML
    private ChoiceBox<String> escolherTG;

    @FXML
    private Label statusEntrega;

    private Map<String, String> feedbackMap = new HashMap<>();
    private Map<String, String> notaMap = new HashMap<>();
    Set<String> listaTG1 ;
    Set<String> listaTG2 ;
    String[] opcoesTG = LayoutEntregaController.getOpcoesChoiceBox();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listaTG1 = new HashSet<>();
        listaTG2 = new HashSet<>();
        for(SubmitModel submit :NotasFeedbackScreen.toDo){
            if(SubmitController.convertForTg(submit.getIdTeam()).equals("TG1")){
                listaTG1.add(submit.getDescription());
            }else {
                listaTG2.add(submit.getDescription());
            }

        }

        escolherTG.getItems().addAll(opcoesTG);
        escolherTG.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                escolhaDeEntrega.getItems().clear(); // Limpar as opções atuais na ChoiceBox

                if (newValue.equals("TG1")) {
                    escolhaDeEntrega.getItems().addAll(listaTG1);
                } else if (newValue.equals("TG2")) {
                    escolhaDeEntrega.getItems().addAll(listaTG2);
                }
            }

        });


        escolhaDeEntrega.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Exibir as notas e feedbacks correspondentes se já existirem
                if (feedbackMap.containsKey(newValue)) {
                    Feedback.setText(feedbackMap.get(newValue));
                } else {
                    Feedback.setText("");
                }
                if (notaMap.containsKey(newValue)) {
                    Nota.setText(notaMap.get(newValue));
                    atualizarStatusEntrega(newValue);
                } else {
                    Nota.setText("");
                    statusEntrega.setText("SEM NOTA");
                    statusEntrega.setStyle("-fx-text-fill: red;");
                }
            }
        });
    }

    @FXML
    void BotaoEnviar(ActionEvent event) {
         System.out.print(NotasFeedbackScreen.toDo);
         System.out.println(NotasFeedbackScreen.display);
         System.out.println(escolhaDeEntrega.getValue());


        String entregaSelecionada = escolhaDeEntrega.getValue();
        if (entregaSelecionada != null) {
            try{
            feedbackMap.put(entregaSelecionada, Feedback.getText());
            notaMap.put(entregaSelecionada, Nota.getText());
            ToDoController.sendDataForDataBase(Feedback.getText(), Nota.getText(), entregaSelecionada, NotasFeedbackScreen.display);
            if (notaMap.containsKey(entregaSelecionada) && !notaMap.get(entregaSelecionada).isEmpty()) {
                atualizarStatusEntrega(entregaSelecionada);
            } else {
                statusEntrega.setText("SEM NOTA");
                statusEntrega.setStyle("-fx-text-fill: red;");
            }} catch (Exception e){

            }
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

    public void setStatusEntrega(Label statusEntrega) {
        this.statusEntrega = statusEntrega;
    }


    private void atualizarStatusEntrega(String entrega) {
        if (notaMap.containsKey(entrega) && !notaMap.get(entrega).isEmpty()) {
            statusEntrega.setText("OK");
            statusEntrega.setStyle("-fx-text-fill: #31efb8;");
        }
    }

} 

