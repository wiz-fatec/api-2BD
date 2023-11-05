package com.tg.manager.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import com.tg.manager.model.DisplayTableModel;
import com.tg.manager.model.ToDoModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class HomeScreenWithTableController implements Initializable {

    @FXML
    private Button ButtonUploadCSV;

    @FXML
    private Button ButtonUploadCertificate;

    @FXML
    private ImageView deliveryScreenHome;

    @FXML
    private ImageView GeneralReportHome;

    @FXML
    private TableColumn<DisplayTableModel, String> emailFatecColumn;

    @FXML
    private TableColumn<DisplayTableModel, Boolean> profileStudentColumn;

    @FXML
    private TableColumn<DisplayTableModel, Boolean> rateAndFeedbackColumn;

    @FXML
    private TableColumn<DisplayTableModel, Boolean> reportColumn;

    @FXML
    private TableColumn<DisplayTableModel, String> nameColumn;

    @FXML
    private TableView<DisplayTableModel> table;

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

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStudent().getName()));
        emailFatecColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStudent().getFatecEmail()));

        profileStudentColumn.setCellFactory(col -> createButtonCell("Visualizar Perfil"));
        rateAndFeedbackColumn.setCellFactory(col -> createButtonCell("Atribuir Nota"));
        reportColumn.setCellFactory(col -> createButtonCell("Visualizar Relatório"));

        loadStudentData();
    }

    private void loadStudentData() {
        Set<DisplayTableModel> student = DisplayTableModel.getDataTable();

        ObservableList<DisplayTableModel> studentList = FXCollections.observableArrayList(student);
        table.setItems(studentList);
    }

    private TableCell<DisplayTableModel, Boolean> createButtonCell(String buttonLabel) {
        return new ButtonCell(buttonLabel);
    }
}

class ButtonCell extends TableCell<DisplayTableModel, Boolean> {
    private final Button button;

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
                    DisplayTableModel displayModel = tableView.getItems().get(index);
                    Set <ToDoModel> toDo = displayModel.getValuesFeedbacks();
                    System.out.println(displayModel);
                    NotasFeedbackScreen notasFeedbackScreen = new NotasFeedbackScreen(toDo);
                    try {
                        notasFeedbackScreen.start(new Stage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "Visualizar Relatório":
                    // Lógica para "Visualizar Relatório" com base no email
                    break;
                case "Visualizar Perfil":
                    // Lógica para "Visualizar Perfil" com base no email
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
}
