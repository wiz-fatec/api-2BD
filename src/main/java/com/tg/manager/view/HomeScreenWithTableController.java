package com.tg.manager.view;

import java.net.URL;
import java.util.ResourceBundle;

import com.tg.manager.model.StudentModel;
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
    private TableColumn<StudentModel, ?> apto;

    @FXML
    private TableColumn<StudentModel, String> institutionalEmail;

    @FXML
    private TableColumn<StudentModel, Boolean> profileStudent;

    @FXML
    private TableColumn<StudentModel, Boolean> rateAndFeedback;

    @FXML
    private TableColumn<StudentModel, Boolean> report;

    @FXML
    private TableColumn<StudentModel, String> student;

    @FXML
    private TableView<StudentModel> table;

    @FXML
    void UploadCSV(ActionEvent event) {

    }

    ObservableList<StudentModel> list = FXCollections.observableArrayList(
        new StudentModel("Paulo Arantes", "Paulo@test.com"),
        new StudentModel("Cauan Barbaglio", "Cauan@test.com")
    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
        student.setCellValueFactory(new PropertyValueFactory<StudentModel, String>("student"));
        institutionalEmail.setCellValueFactory(new PropertyValueFactory<StudentModel, String>("institutionalEmail"));
        
        rateAndFeedback.setCellFactory(col -> new ButtonCell());
        report.setCellFactory(col -> new ButtonCell());
        profileStudent.setCellFactory(col -> new ButtonCell());

        rateAndFeedback.setCellFactory(col -> {
            TableCell<StudentModel, Boolean> cell = new ButtonCell();
            cell.setAlignment(Pos.CENTER);
            return cell;
        });

        report.setCellFactory(col -> {
            TableCell<StudentModel, Boolean> cell = new ButtonCell();
            cell.setAlignment(Pos.CENTER);
            return cell;
        });

        profileStudent.setCellFactory(col -> {
            TableCell<StudentModel, Boolean> cell = new ButtonCell();
            cell.setAlignment(Pos.CENTER);
            return cell;
        });

        table.setItems(list);
    }
}
