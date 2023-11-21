package com.tg.manager.view;

import com.tg.manager.controller.SubmitController;
import com.tg.manager.model.SubmitModel;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
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
    private TableColumn<Entrega, String> TGModelo;

    @FXML
    private TableColumn<Entrega, Boolean> DeleteDelivery;

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

    @FXML
    private ChoiceBox<String> ModeloTg;

    private static String[] opcoesChoiceBox = {"TG1", "TG2"};
    private static String[] opcoesModeloTg = {"Estágio - Técnico", "Técnico - Disciplina", "Científico", "Portfólio"};
    public static String[] getOpcoesChoiceBox() {
        return opcoesChoiceBox;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) { 
        ObservableList<Entrega> entregaList = FXCollections.observableArrayList();
        tabela.setItems(entregaList);       
        tabela.setItems(SubmitController.getDataInDataBase());
        tipoDeTg.getItems().addAll(opcoesChoiceBox);
        ModeloTg.getItems().addAll(opcoesModeloTg);
        dataInicial.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.isBefore(LocalDate.now()));
            }
        });

        dataFinal.setDisable(true);

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
                    dataFinal.setDisable(false);
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
                    dataFinal.setDisable(true);
                    dataFinal.setValue(null);
                }
            }
        });

        botaoEnviar.setDisable(true);

        nomeDaAtividade.textProperty().addListener((observable, oldValue, newValue) -> checkCampos());
        tipoDeTg.valueProperty().addListener((observable, oldValue, newValue) -> checkCampos());
        dataInicial.valueProperty().addListener((observable, oldValue, newValue) -> checkCampos());
        dataFinal.valueProperty().addListener((observable, oldValue, newValue) -> checkCampos());
        ModeloTg.valueProperty().addListener((observable, oldValue, newValue) -> checkCampos());
        Atividade.setCellValueFactory(new PropertyValueFactory<>("atividade"));
        DataFinal.setCellValueFactory(new PropertyValueFactory<>("dataFinal"));
        DataInicial.setCellValueFactory(new PropertyValueFactory<>("dataInicial"));
        TipoTG.setCellValueFactory(new PropertyValueFactory<>("tipoTG"));
        TGModelo.setCellValueFactory(new PropertyValueFactory<>("tgModelo"));

        DeleteDelivery.setCellValueFactory(param -> new SimpleBooleanProperty(true));
        DeleteDelivery.setCellFactory(col -> createDeleteButtonCell("Excluir"));
    }

    private TableCell<Entrega, Boolean> createDeleteButtonCell(String buttonLabel) {
        return new DeleteButtonCell(buttonLabel, tabela);
    }    

    private class DeleteButtonCell extends TableCell<Entrega, Boolean> {
        private final Button button;
        private final TableView<Entrega> tabela;
    
        public DeleteButtonCell(String buttonLabel, TableView<Entrega> tabela) {
            this.button = new Button(buttonLabel);
            this.tabela = tabela;
    
            button.setOnAction(event -> {
                Entrega entrega = getTableView().getItems().get(getIndex());
                int entregaId = entrega.getId();

                SubmitModel.deleteSubmit(entregaId);
    
                tabela.setItems(SubmitController.getDataInDataBase());
            });
        }

        @Override
        protected void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || !item) {
                setGraphic(null);
            } else {
                setAlignment(Pos.CENTER);
                setGraphic(button);
            }
        }
    }

    private void checkCampos() {
        String atividade = nomeDaAtividade.getText();
        String tg = tipoDeTg.getValue();
        String modelo = ModeloTg.getValue();
        LocalDate dataInicialValue = dataInicial.getValue();
        LocalDate dataFinalValue = dataFinal.getValue();

        boolean camposPreenchidos = atividade != null && !atividade.isEmpty() &&
                tg != null &&
                modelo != null &&
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
            String modeloDeTg = ModeloTg.getValue();


            Entrega novaEntrega = new Entrega(atividade, tg, inicialData, finalData, modeloDeTg);
            SubmitController.setDataInDataBase(novaEntrega);
            tabela.setItems(SubmitController.getDataInDataBase());
            nomeDaAtividade.clear();
            tipoDeTg.getSelectionModel().clearSelection();
            dataInicial.setValue(null);
            dataFinal.setValue(null);
            botaoEnviar.setDisable(true);
            dataFinal.setDisable(true);
            minhaInicialDataFormatada = null;
            minhaFinalDataFormatada = null;
            ModeloTg.getSelectionModel().clearSelection();
        } else {

        }
    }

    private boolean camposPreenchidos() {
        return nomeDaAtividade.getText() != null && !nomeDaAtividade.getText().isEmpty() &&
                tipoDeTg.getValue() != null &&
                dataInicial.getValue() != null &&
                dataFinal.getValue() != null &&
                minhaInicialDataFormatada != null &&
                minhaFinalDataFormatada != null &&
                ModeloTg.getValue() != null;
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeScreenWithTable.fxml"));
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
