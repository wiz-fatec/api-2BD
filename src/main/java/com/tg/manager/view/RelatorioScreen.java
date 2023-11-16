package com.tg.manager.view;

import java.util.Set;

import com.tg.manager.model.DisplayTableModel;
import com.tg.manager.model.SubmitModel;
import com.tg.manager.model.ToDoModel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RelatorioScreen extends Application {
    public  static DisplayTableModel display1;
    public RelatorioScreen(){}

    public RelatorioScreen(DisplayTableModel display1) {
        this.display1 = display1;
        }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //TODO condicional para definir qual o tipo de relatório se deve abrir usando aluno como parâmetro.
        String relatorio;
        relatorio = "RelatorioPortfolio.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(relatorio)); 
        Parent root = fxmlLoader.load();
        Scene tela1 = new Scene(root);

        primaryStage.setScene(tela1);
        primaryStage.setResizable(false);
        primaryStage.show();


    }
}
