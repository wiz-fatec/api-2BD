package com.tg.manager.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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
    private Label statusEntrega;

    

    private List<String> mockadoListaTG1 = new ArrayList<>();
    private List<String> mockadoListaTG2 = new ArrayList<>();
    private Map<String, String> feedbackMap = new HashMap<>();
    private Map<String, String> notaMap = new HashMap<>();



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mockadoListaTG1.add("entrega 1");
        mockadoListaTG1.add("entrega 2");
        mockadoListaTG1.add("entrega 3");
        mockadoListaTG2.add("entrega 4");
        mockadoListaTG2.add("entrega 5");
        mockadoListaTG2.add("entrega 6");

        String mockadoTipoTG = "TG2";

        if (mockadoTipoTG.equals("TG1")){
            escolhaDeEntrega.getItems().addAll(mockadoListaTG1);

        }
        if (mockadoTipoTG.equals("TG2")){
            escolhaDeEntrega.getItems().addAll(mockadoListaTG2);

        }

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
        String entregaSelecionada = escolhaDeEntrega.getValue();
        if (entregaSelecionada != null) {
            // Adicione as notas e feedbacks ao mapa correspondente
            feedbackMap.put(entregaSelecionada, Feedback.getText());
            notaMap.put(entregaSelecionada, Nota.getText());
    
            // Verifique se a nota está presente e atualize o status de acordo
            if (notaMap.containsKey(entregaSelecionada) && !notaMap.get(entregaSelecionada).isEmpty()) {
                atualizarStatusEntrega(entregaSelecionada);
            } else {
                statusEntrega.setText("SEM NOTA");
                statusEntrega.setStyle("-fx-text-fill: red;");
            }
        }
    }

    @FXML
    void goToDeliveryScreen(MouseEvent event) {

    }

    @FXML
    void goToGeneralReportScreen(MouseEvent event) {

    }

    @FXML
    void goToHomeScreen(MouseEvent event) {

    }

    public TextField getFeedback() {
        return Feedback;
    }


    public void setFeedback(TextField feedback) {
        Feedback = feedback;
    }


    public TextField getNota() {
        return Nota;
    }


    public void setNota(TextField nota) {
        Nota = nota;
    }


    public Label getStatusEntrega() {
        return statusEntrega;
    }


    public void setStatusEntrega(Label statusEntrega) {
        this.statusEntrega = statusEntrega;
    }


    private void atualizarStatusEntrega(String entrega) {
        if (notaMap.containsKey(entrega) && !notaMap.get(entrega).isEmpty()) {
            statusEntrega.setText("OK");
            statusEntrega.setStyle("-fx-text-fill: green;");
        }
    }

} 

