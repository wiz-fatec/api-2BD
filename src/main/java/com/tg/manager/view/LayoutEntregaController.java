package com.tg.manager.view;

import com.tg.manager.controller.SubmitController;
import com.tg.manager.model.SubmitModel;
import com.tg.manager.model.TeamModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

public class LayoutEntregaController implements Initializable {
    String minhaInicialDataFormatada;
    String minhaFinalDataFormatada;
    LocalDate myInicialDate;
    LocalDate myfinalDate;
    private static List<String> listaTG1 = new ArrayList<>();
    private static List<String> listaTG2 = new ArrayList<>();

    @FXML
    private ImageView botaoCalendar;

    @FXML
    private ImageView botaoClipboard;

    @FXML
    private Button botaoEnviar;

    @FXML
    private ImageView botaoHome;

    @FXML
    private TableColumn<Entrega, String> Atividade;

    @FXML
    private TableColumn<Entrega, String> DataFinal;

    @FXML
    private TableColumn<Entrega, String> DataInicial;

    @FXML
    private TableColumn<Entrega, String> TipoTG;

    @FXML
    private DatePicker dataFinal;

    @FXML
    private DatePicker dataInicial;

    @FXML
    private TextField nomeDaAtividade;

    @FXML
    private TableView<Entrega> tabela;

    @FXML
    private ChoiceBox<String> tipoDeTg;

    private static String[] opcoesChoiceBox = {"TG1", "TG2"};
    
    public static String[] getOpcoesChoiceBox() {
        return opcoesChoiceBox;
    }

    private ObservableList<Entrega> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {        
        tabela.setItems(SubmitController.getDataInDataBase());
        tipoDeTg.getItems().addAll(opcoesChoiceBox);

        dataInicial.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.isBefore(LocalDate.now()));
            }
        });

        dataFinal.setDisable(true); // Desabilita a data final inicialmente

        dataFinal.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    public void updateItem(LocalDate date, boolean empty) {
                        super.updateItem(date, empty);
                        if (dataInicial.getValue() != null && date.isBefore(dataInicial.getValue().plusDays(1))) {
                            setDisable(true);
                        }
                    }
                };
            }
        });

        dataInicial.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                if (newValue != null) {
                    final LocalDate selectedDate = newValue;
                    dataFinal.setDisable(false); // Habilita a data final quando a data inicial é definida
                    dataFinal.setDayCellFactory(new Callback<DatePicker, DateCell>() {
                        public DateCell call(final DatePicker datePicker) {
                            return new DateCell() {
                                public void updateItem(LocalDate date, boolean empty) {
                                    super.updateItem(date, empty);
                                    if (selectedDate != null && date.isBefore(selectedDate.plusDays(1))) {
                                        setDisable(true);
                                    }
                                }
                            };
                        }
                    });
                } else {
                    dataFinal.setDisable(true); // Desabilita a data final se a data inicial estiver vazia
                    dataFinal.setValue(null); // Reseta a data final se a data inicial estiver vazia
                }
            }
        });

        botaoEnviar.setDisable(true);

        nomeDaAtividade.textProperty().addListener((observable, oldValue, newValue) -> checkCampos());
        tipoDeTg.valueProperty().addListener((observable, oldValue, newValue) -> checkCampos());
        dataInicial.valueProperty().addListener((observable, oldValue, newValue) -> checkCampos());
        dataFinal.valueProperty().addListener((observable, oldValue, newValue) -> checkCampos());

        Atividade.setCellValueFactory(new PropertyValueFactory<>("atividade"));
        DataFinal.setCellValueFactory(new PropertyValueFactory<>("dataFinal"));
        DataInicial.setCellValueFactory(new PropertyValueFactory<>("dataInicial"));
        TipoTG.setCellValueFactory(new PropertyValueFactory<>("tipoTG"));
    }

    private void checkCampos() {
        String atividade = nomeDaAtividade.getText();
        String tg = tipoDeTg.getValue();
        LocalDate dataInicialValue = dataInicial.getValue();
        LocalDate dataFinalValue = dataFinal.getValue();

        boolean camposPreenchidos = atividade != null && !atividade.isEmpty() &&
                tg != null &&
                dataInicialValue != null &&
                dataFinalValue != null;

        botaoEnviar.setDisable(!camposPreenchidos);
    }
    
    @FXML
    void enviarParaValidacao(ActionEvent event) {
        if (camposPreenchidos()) {
            String atividade = nomeDaAtividade.getText();
            String tg = tipoDeTg.getValue().toString();
            String inicialData = minhaInicialDataFormatada;
            String finalData = minhaFinalDataFormatada;

            Entrega novaEntrega = new Entrega(atividade, tg, inicialData, finalData);
            SubmitController.setDataInDataBase(novaEntrega);
            tabela.setItems(SubmitController.getDataInDataBase());
            // Limpa os campos após adicionar à tabela
            nomeDaAtividade.clear();
            tipoDeTg.getSelectionModel().clearSelection();
            dataInicial.setValue(null);
            dataFinal.setValue(null);
            botaoEnviar.setDisable(true);
            dataFinal.setDisable(true);
            minhaInicialDataFormatada = null;
            minhaFinalDataFormatada = null;
        } else {
            // Lógica de tratamento para campos vazios
            // Por exemplo, exibir uma mensagem de erro ao usuário
        }
    }

    private boolean camposPreenchidos() {
        return nomeDaAtividade.getText() != null && !nomeDaAtividade.getText().isEmpty() &&
                tipoDeTg.getValue() != null &&
                dataInicial.getValue() != null &&
                dataFinal.getValue() != null &&
                minhaInicialDataFormatada != null &&
                minhaFinalDataFormatada != null;
    }

    @FXML
    void inseridoDataInicial(ActionEvent event) {
        if(dataInicial.getValue() == null) {
            return;
        }

        LocalDate myInicialDate = dataInicial.getValue();
        minhaInicialDataFormatada = myInicialDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    @FXML
    void inseridoDataFinal(ActionEvent event) {
        if(dataFinal.getValue() == null) {
            return;
        }
        
        LocalDate date = dataFinal.getValue();
        minhaFinalDataFormatada = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }


    @FXML
    void goToGeneralReportScreen(MouseEvent event) {
        
    }

    @FXML
    void goToHomeScreen(MouseEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("NotasFeedback.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) botaoHome.getScene().getWindow();
        stage.setScene(scene);
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    public static List<String> getListaTG1() {
        return listaTG1;
    }

    public static List<String> getListaTG2() {
        return listaTG2;
    }

    }
