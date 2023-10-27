package com.tg.manager.view;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.tg.manager.model.StudentModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private TableColumn<StudentModel, String> emailFatec;

    @FXML
    private TableColumn<StudentModel, Boolean> profileStudent;

    @FXML
    private TableColumn<StudentModel, Boolean> rateAndFeedback;

    @FXML
    private TableColumn<StudentModel, Boolean> report;

    @FXML
    private TableColumn<StudentModel, String> name;

    @FXML
    private TableView<StudentModel> table;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setCellValueFactory(new PropertyValueFactory<StudentModel, String>("name"));
        emailFatec.setCellValueFactory(new PropertyValueFactory<StudentModel, String>("fatecEmail"));

        rateAndFeedback.setCellFactory(col -> createButtonCell("Atribuir"));
        report.setCellFactory(col -> createButtonCell("Visualizar"));
        profileStudent.setCellFactory(col -> createButtonCell("Visualizar"));

        carregarDadosDosAlunos();
    }

    private void carregarDadosDosAlunos() {
        StudentModel studentModel = new StudentModel();
        try {
            ObservableList<StudentModel> alunos = FXCollections.observableArrayList(studentModel.getSubmit());
            table.setItems(alunos);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private TableCell<StudentModel, Boolean> createButtonCell(String buttonLabel) {
        return new CustomButtonCell(buttonLabel);
    }
}

class CustomButtonCell extends TableCell<StudentModel, Boolean> {
    private final Button button;

    public CustomButtonCell(String buttonLabel) {
        button = new Button(buttonLabel);
        button.setAlignment(getAlignment());;
        button.setOnAction(event -> {
            switch (buttonLabel) {
                case "Rate and Feedback":

                    break;
                case "Report":

                    break;
                case "Profile":
   
                    break;
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
