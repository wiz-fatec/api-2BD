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

    private Integer idTeam;

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

    @FXML
    private Label nomeAluno;

    @FXML
    private Label LabelTg;

    private Map<String, String> feedbackMap = new HashMap<>();
    private Map<String, String> notaMap = new HashMap<>();
    private Set<String> listaTG1;
    private Set<String> listaTG2;
    private String[] opcoesTG = LayoutEntregaController.getOpcoesChoiceBox();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LabelTg.setText(ButtonCell.getDisplayModel().getTypeTg());
        nomeAluno.setText("Nome: " + ButtonCell.getDisplayModel().getStudent().getName());
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

                atualizarLabels();
            }
        });

        escolhaDeEntrega.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                Integer idStudent = NotasFeedbackScreen.display.getStudent().getId();
                Integer idTeam = NotasFeedbackScreen.display.getStudent().getTeamId();

                ToDoModel noteAndFeedback = ToDoModel.searchNoteAndFeedback(idStudent, newValue, idTeam);

                if (noteAndFeedback != null) {
                    Feedback.setText(noteAndFeedback.getFeedback());
                    FeedbackLabel.setText("Feedback: " + noteAndFeedback.getFeedback());

                    Nota.setText(String.valueOf(noteAndFeedback.getNote()));
                    NotaLabel.setText("Nota: " + noteAndFeedback.getNote());
                    atualizarStatusEntrega(newValue);
                } else {
                    Feedback.setText("");
                    FeedbackLabel.setText("Feedback: ");
                    Nota.setText("");
                    NotaLabel.setText("Nota: ");
                    statusEntrega.setText("SEM NOTA");
                    statusEntrega.setStyle("-fx-text-fill: red;");
                }
            }
        });

        atualizarLabels();
    }

    private void limparLabels() {
        Feedback.setText("");
        FeedbackLabel.setText("Feedback: ");
        Nota.setText("");
        NotaLabel.setText("Nota: ");
        statusEntrega.setText("SEM NOTA");
        statusEntrega.setStyle("-fx-text-fill: red;");
    }

    private void atualizarLabels() {
        String entregaSelecionada = escolhaDeEntrega.getValue();
        String tgSelecionado = escolherTG.getValue();

        if (entregaSelecionada != null && tgSelecionado != null) {
            Integer idStudent = NotasFeedbackScreen.display.getStudent().getId();

            ToDoModel noteAndFeedback = ToDoModel.searchNoteAndFeedback(idStudent, entregaSelecionada, idTeam);

            if (noteAndFeedback != null) {
                feedbackMap.put(entregaSelecionada, noteAndFeedback.getFeedback());
                notaMap.put(entregaSelecionada, String.valueOf(noteAndFeedback.getNote()));

                FeedbackLabel.setText("Feedback: " + noteAndFeedback.getFeedback());
                NotaLabel.setText("Nota: " + noteAndFeedback.getNote());

                atualizarStatusEntrega(entregaSelecionada);
            } else {
                limparLabels();
            }
        }
    }

    @FXML
    void BotaoEnviar(ActionEvent event) {
        // System.out.print(NotasFeedbackScreen.toDo);
        // System.out.println(NotasFeedbackScreen.display);
        // System.out.println(escolhaDeEntrega.getValue());
        // System.out.println(NotasFeedbackScreen.display.getStudent().getId());
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

                Integer idStudent = NotasFeedbackScreen.display.getStudent().getId();
                Integer idTeam = NotasFeedbackScreen.display.getStudent().getTeamId();
                ToDoModel noteAndFeedback = ToDoModel.searchNoteAndFeedback(idStudent, entregaSelecionada, idTeam);

                if (noteAndFeedback != null) {

                    feedbackMap.put(entregaSelecionada, noteAndFeedback.getFeedback());
                    notaMap.put(entregaSelecionada, String.valueOf(noteAndFeedback.getNote()));
                }

                feedbackMap.put(entregaSelecionada, Feedback.getText());
                notaMap.put(entregaSelecionada, Nota.getText());
                ToDoController.sendDataForDataBase(Feedback.getText(), Nota.getText(), entregaSelecionada, NotasFeedbackScreen.display);

                if (notaMap.containsKey(entregaSelecionada) && !notaMap.get(entregaSelecionada).isEmpty()) {
                    atualizarStatusEntrega(entregaSelecionada);
                } else {
                    statusEntrega.setText("SEM NOTA");
                    statusEntrega.setStyle("-fx-text-fill: red;");
                }
            } catch (Exception e) {
                e.printStackTrace();
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
