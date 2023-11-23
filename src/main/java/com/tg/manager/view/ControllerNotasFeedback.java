package com.tg.manager.view;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import javax.crypto.spec.RC2ParameterSpec;

import com.tg.manager.controller.SubmitController;
import com.tg.manager.controller.ToDoController;
import com.tg.manager.model.StudentModel;
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
    private Label FeedbackLabel;

    @FXML
    private TextField Nota;

    @FXML
    private Label NotaLabel;

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
    private Set<String> listaTG1;
    private Set<String> listaTG2;
    private String[] opcoesTG = LayoutEntregaController.getOpcoesChoiceBox();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listaTG1 = new HashSet<>();
        listaTG2 = new HashSet<>();
        for (SubmitModel submit : NotasFeedbackScreen.toDo) {
            if (SubmitController.convertForTg(submit.getIdTeam()).equals("TG1")) {
                listaTG1.add(submit.getDescription());
            } else {
                listaTG2.add(submit.getDescription());
            }
        }

        escolherTG.getItems().addAll(opcoesTG);
        escolherTG.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                escolhaDeEntrega.getItems().clear();

                if (newValue.equals("TG1")) {
                    escolhaDeEntrega.getItems().addAll(listaTG1);
                } else if (newValue.equals("TG2")) {
                    escolhaDeEntrega.getItems().addAll(listaTG2);
                }
            }
        });

        escolhaDeEntrega.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (feedbackMap.containsKey(newValue)) {
                    Feedback.setText(feedbackMap.get(newValue));
                    FeedbackLabel.setText("Feedback: " + feedbackMap.get(newValue));
                } else {
                    Feedback.setText("");
                    FeedbackLabel.setText("Feedback: ");
                }
                if (notaMap.containsKey(newValue)) {
                    Nota.setText(notaMap.get(newValue));
                    NotaLabel.setText("Nota: " + notaMap.get(newValue));
                    atualizarStatusEntrega(newValue);
                } else {
                    Nota.setText("");
                    NotaLabel.setText("Nota: ");
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
        System.out.println(NotasFeedbackScreen.display.getStudent().getId());
        String entregaSelecionada = escolhaDeEntrega.getValue();
        if (entregaSelecionada != null) {
            try {
                String textFeedback = String.valueOf(Feedback.getText());
                double valorNota = Double.parseDouble(Nota.getText());
                if (valorNota < 0 || valorNota > 10) {
                    GradeAndFeedbackAlert.showWarningAlert();
                    return;
                } else if (textFeedback.isEmpty()) {
                    GradeAndFeedbackAlert.showCommentWarningAlert();
                    return;
                }

                feedbackMap.put(entregaSelecionada, Feedback.getText());
                notaMap.put(entregaSelecionada, Nota.getText());
                ToDoController.sendDataForDataBase(Feedback.getText(), Nota.getText(), entregaSelecionada,
                        NotasFeedbackScreen.display);

                if (notaMap.containsKey(entregaSelecionada) && !notaMap.get(entregaSelecionada).isEmpty()) {
                    atualizarStatusEntrega(entregaSelecionada);
                } else {
                    statusEntrega.setText("SEM NOTA");
                    statusEntrega.setStyle("-fx-text-fill: red;");
                }
            } catch (Exception e) {
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
        StudentModel.getReport();
        GeneralReportAlert.showInformationAlert();
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