package com.tg.manager.view;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

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
    private Label statusEntrega;

    private Map<String, String> feedbackMap = new HashMap<>();
    private Map<String, String> notaMap = new HashMap<>();
    List<String> listaTG1 = LayoutEntregaController.getListaTG1();
    List<String> listaTG2 = LayoutEntregaController.getListaTG2();
    @Override
    public void initialize(URL location, ResourceBundle resources) {


        String mockadoDoAlunoTipoTG = "TG2";

        if (mockadoDoAlunoTipoTG.equals("TG1")){
            escolhaDeEntrega.getItems().addAll(listaTG1);

        }
        if (mockadoDoAlunoTipoTG.equals("TG2")){
            escolhaDeEntrega.getItems().addAll(listaTG2);

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
            statusEntrega.setStyle("-fx-text-fill: green;");
        }
    }

} 

