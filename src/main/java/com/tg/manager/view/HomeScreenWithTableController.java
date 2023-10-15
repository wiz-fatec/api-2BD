package com.tg.manager.view;

import java.net.URL;
import java.util.ResourceBundle;

import com.tg.manager.utils.ButtonCell;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class HomeScreenWithTableController implements Initializable {

    @FXML
    private Button ButtonUploadCSV;

    @FXML
    private Button ButtonUploadCertificate;

    @FXML
    private TableColumn<StudentMock, ?> apto;

    @FXML
    private TableColumn<StudentMock, String> institutionalEmail;

    @FXML
    private TableColumn<StudentMock, Boolean> profileStudent;

    @FXML
    private TableColumn<StudentMock, Boolean> rateAndFeedback;

    @FXML
    private TableColumn<StudentMock, Boolean> report;

    @FXML
    private TableColumn<StudentMock, String> student;

    @FXML
    private TableView<StudentMock> table;

    @FXML
    void UploadCSV(ActionEvent event) {

    }

    ObservableList<StudentMock> list = FXCollections.observableArrayList(
        new StudentMock("Paulo Arantes", "Paulo@test.com"),
        new StudentMock("Cauan Barbaglio", "Cauan@test.com")
    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
        student.setCellValueFactory(new PropertyValueFactory<StudentMock, String>("student"));
        institutionalEmail.setCellValueFactory(new PropertyValueFactory<StudentMock, String>("institutionalEmail"));
        
        rateAndFeedback.setCellFactory(col -> new ButtonCell());
        report.setCellFactory(col -> new ButtonCell());
        profileStudent.setCellFactory(col -> new ButtonCell());

        rateAndFeedback.setCellFactory(col -> {
            TableCell<StudentMock, Boolean> cell = new ButtonCell();
            cell.setAlignment(Pos.CENTER);
            return cell;
        });

        report.setCellFactory(col -> {
            TableCell<StudentMock, Boolean> cell = new ButtonCell();
            cell.setAlignment(Pos.CENTER);
            return cell;
        });

        profileStudent.setCellFactory(col -> {
            TableCell<StudentMock, Boolean> cell = new ButtonCell();
            cell.setAlignment(Pos.CENTER);
            return cell;
        });

        table.setItems(list);
    }
}
