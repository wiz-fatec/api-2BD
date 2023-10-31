package com.tg.manager.view;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.util.List;

import com.tg.manager.controller.CSVHandler;
import com.tg.manager.utils.CSVProcessor;

public class HomeScreenController {

    @FXML
    private Button ButtonUploadCSV;

    @FXML
    void UploadCSV(ActionEvent event) {
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
}
