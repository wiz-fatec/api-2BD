package com.tg.manager.model;

import com.tg.manager.model.connection.ConnectionDataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;

public class SubmitModel {
    private String description;
    private Date initial_date;
    private Date final_date;
    private Integer idToDo;

    public void addSubmit(String description, Date initial_date, Date final_date, Integer idToDo) {

        try {
            ConnectionDataBase connectionDb = new ConnectionDataBase();
            Connection connection = connectionDb.getConexao();
            String insercaoSQL = "INSERT INTO entrega (description, initial_date, final_date, idToDo) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insercaoSQL);
            preparedStatement.setString(1, description);
            preparedStatement.setDate(2, initial_date);
            preparedStatement.setDate(3, final_date);
            preparedStatement.setInt(4, idToDo);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            System.out.println("Dados inseridos com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
