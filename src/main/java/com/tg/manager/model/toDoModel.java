package com.tg.manager.model;

import com.tg.manager.model.connection.ConnectionDataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ToDoModel {
    private String feedback;
    private Integer notes;
    private Integer idToDo;

    public void addToDo(String feedbacks, Integer notes, Integer idToDo) {

        try {
            ConnectionDataBase connectionDb = new ConnectionDataBase();
            Connection connection = connectionDb.getConexao();
            String insercaoSQL = "INSERT INTO entrega_aluno (feedbacks, notes, idToDo) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insercaoSQL);
            preparedStatement.setString(1, feedbacks);
            preparedStatement.setInt(2, notes);
            preparedStatement.setInt(3, idToDo);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            System.out.println("Dados inseridos com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}