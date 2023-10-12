package com.tg.manager.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

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

            System.out.print("[");
            boolean first = true;
            for (List<String> innerList : csvData) {
                if (!first) {
                    System.out.print(", ");
                } else {
                    first = false;
                }
                System.out.print("[");
                boolean innerFirst = true;
                for (String value : innerList) {
                    if (!innerFirst) {
                        System.out.print(", ");
                    } else {
                        innerFirst = false;
                    }
                    System.out.print("\"" + value + "\"");
                }
                System.out.print("]");
            }
            System.out.print("]");
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
