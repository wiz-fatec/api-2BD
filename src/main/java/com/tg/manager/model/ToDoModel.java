package com.tg.manager.model;

import com.tg.manager.model.connection.ConnectionDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ToDoModel {
    private String feedback;
    private Double note;
    private Integer idStudent;
    private Integer idIssue;

    public void addToDo(String feedback, Double note, Integer idStudent, Integer idIssue) {
        try {
            ConnectionDataBase connectionDb = new ConnectionDataBase();
            Connection connection = connectionDb.getConexao();
            String insercaoSQL = "INSERT INTO valor_entrega (nota, feedback, idaluno, identrega) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insercaoSQL);
            preparedStatement.setDouble(1, note);
            preparedStatement.setString(2, feedback);
            preparedStatement.setInt(3, idStudent);
            preparedStatement.setInt(4, idIssue);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            System.out.println("Dados inseridos com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
