package com.tg.manager.view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import com.tg.manager.controller.CSVHandler;
import com.tg.manager.model.AdvisorModel;
import com.tg.manager.model.DisplayTableModel;
import com.tg.manager.model.StudentModel;
import com.tg.manager.model.SubmitModel;
import com.tg.manager.utils.CSVProcessor;
import com.tg.manager.utils.ReportPdf;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class HomeScreenWithTableController implements Initializable {

    @FXML
    private Button ButtonUploadCSV;

    @FXML
    private Button ButtonDownloadCertificate;

    @FXML
    private Button ButtonStudentEligible;

    @FXML
    private Button ButtonDownloadDeliveryGrade;

    @FXML
    private ImageView deliveryScreenHome;

    @FXML
    private ImageView GeneralReportHome;

    @FXML
    private TableColumn<DisplayTableModel, String> emailFatecColumn;

    @FXML
    private ComboBox<String> filterTG;

    @FXML
    private TableColumn<DisplayTableModel, String> typeTgColumn;

    @FXML
    private TableColumn<DisplayTableModel, Boolean> profileStudentColumn;

    @FXML
    private TableColumn<DisplayTableModel, Boolean> rateAndFeedbackColumn;

    @FXML
    private TableColumn<DisplayTableModel, Boolean> reportColumn;

    @FXML
    private TableColumn<DisplayTableModel, String> nameColumn;

    @FXML
    private TableColumn<DisplayTableModel, String> aptColumn;

    @FXML
    private TableView<DisplayTableModel> table;

    @FXML
    void DownloadDeliveryGrade(ActionEvent event) {

        ReportPdf.reportReuseNotes(StudentModel.getSubmit());
        GeneralReportAlert.showInformationAlert();

    }

    @FXML
    void UploadNewCSV(ActionEvent event) {
        File file = escolherArquivoCSV();
        if (file != null) {
            System.out.println("Arquivo selecionado: " + file.getAbsolutePath());

            List<List<String>> csvData = CSVProcessor.readCSVToListOfLists(file.getAbsolutePath());

            CSVHandler.populateDataBase(csvData);
            abrirTelaHomeScreenWithTable();
        }
    }

    private void abrirTelaHomeScreenWithTable() {
        HomeScreenWithTable homeScreenWithTable = new HomeScreenWithTable();
        try {
            homeScreenWithTable.start(new Stage());
            ((Stage) ButtonUploadCSV.getScene().getWindow()).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private File escolherArquivoCSV() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        fileChooser.setTitle("Selecionar arquivo CSV");

        Stage stage = (Stage) ButtonUploadCSV.getScene().getWindow();
        return fileChooser.showOpenDialog(stage);
    }

    @FXML
    void DownloadCertificate(ActionEvent event) {
        AdvisorModel.reportCertified();
        GeneralReportAlert.showInformationAlert();
    }

    @FXML
    void DownloadStudentEligible(ActionEvent event) {
        DisplayTableModel.reportIsApt();
        GeneralReportAlert.showInformationAlert();
    }

    @FXML
    void filterStudentTG(ActionEvent event) {
        String selectedTypeTg = filterTG.getValue();

        if ("Sem filtro".equals(selectedTypeTg)) {
            loadStudentData();
        } else if (selectedTypeTg != null) {
            Set<DisplayTableModel> filteredList = DisplayTableModel.filterTable(selectedTypeTg);
    
            ObservableList<DisplayTableModel> filteredStudentList = FXCollections.observableArrayList(filteredList);
            table.setItems(filteredStudentList);
        }
    }

    @FXML
    void goToDeliveryScreenHome(MouseEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("NovaEntregaScreen.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) deliveryScreenHome.getScene().getWindow();
        stage.setScene(scene);
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    @FXML
    void goToGeneralReportScreenHome(MouseEvent event) {
        StudentModel.getReport();
        GeneralReportAlert.showInformationAlert();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStudent().getName()));
        emailFatecColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStudent().getFatecEmail()));
        typeTgColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTypeTg()));
        aptColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getIsApt() ? "APTO" : "NÃO APTO"));
        profileStudentColumn.setCellFactory(col -> createButtonCell("Visualizar Perfil"));
        rateAndFeedbackColumn.setCellFactory(col -> createButtonCell("Atribuir Nota"));
        reportColumn.setCellFactory(col -> createButtonCell("Visualizar Relatório"));

        initComboBox();

        loadStudentData();
    }

    private void initComboBox() {
    ObservableList<String> typesList = FXCollections.observableArrayList(
            "Sem filtro", "Portfólio", "Estágio - Técnico", "Técnico - Disciplina", "Científico"
    );
    filterTG.setItems(typesList);
    }

    private void loadStudentData() {
        Set<DisplayTableModel> student = DisplayTableModel.getDataTable();

        ObservableList<DisplayTableModel> studentList = FXCollections.observableArrayList(student);
        table.setItems(studentList);
    }

    private TableCell<DisplayTableModel, Boolean> createButtonCell(String buttonLabel) {
        return new ButtonCell(buttonLabel);
    }

    public static DisplayTableModel getDisplayModel1() {
        return ButtonCell.getDisplayModel1();
    }
    

}

class ButtonCell extends TableCell<DisplayTableModel, Boolean> {
    private final Button button;
    public Button getButton() {
        return button;
    }
    private static DisplayTableModel  displayModel;
    private static DisplayTableModel  displayModel1;
    private static DisplayTableModel  displayModel2;

    public ButtonCell(String buttonLabel) {
        button = new Button(buttonLabel);
        button.setAlignment(Pos.CENTER);

    button.setOnAction(event -> {
        TableView<DisplayTableModel> tableView = getTableView();
        int index = getIndex();
        if (index >= 0 && index < tableView.getItems().size()) {

            
            switch (buttonLabel) {
                case "Atribuir Nota":
                    Stage currentStage = (Stage) button.getScene().getWindow();
                    currentStage.close();
                    displayModel = tableView.getItems().get(index);
                    Set <SubmitModel> toDo = displayModel.getValuesFeedbacks();
//                    System.out.println(displayModel);
                    NotasFeedbackScreen notasFeedbackScreen = new NotasFeedbackScreen(toDo,displayModel);
                    try {
                        notasFeedbackScreen.start(new Stage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "Visualizar Relatório":
                    Stage currentStage1 = (Stage) button.getScene().getWindow();
                    currentStage1.close();
                    displayModel1 = tableView.getItems().get(index);
                    System.out.println(displayModel1);
                    RelatorioScreen RelatorioScreen = new RelatorioScreen(displayModel1);
                    try {
                        RelatorioScreen.start(new Stage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "Visualizar Perfil":
                    Stage currentStage2 = (Stage) button.getScene().getWindow();
                    currentStage2.close();
                    displayModel2 = tableView.getItems().get(index);
                    System.out.println(displayModel2);
                    PerfilAlunoScreen PerfilAlunoScreen = new PerfilAlunoScreen(displayModel2);
                    try {
                        PerfilAlunoScreen.start(new Stage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                break;
            }
        }
    });
    }

    @Override
    protected void updateItem(Boolean item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setGraphic(null);
        } else {
            setAlignment(Pos.CENTER);
            setGraphic(button);
        }
    }

    public static DisplayTableModel getDisplayModel1() {
        return displayModel1;
    }
    
    public static DisplayTableModel getDisplayModel2() {
        return displayModel2;
    }

    public static DisplayTableModel getDisplayModel() {
        return displayModel;
    }

}
