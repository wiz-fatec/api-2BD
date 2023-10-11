package com.tg.manager.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.tg.manager.model.connection.ConnectionDataBase;

public class TeamModel {
    private Integer semester;
    private Integer year;
    private Integer idIssue;

    public void addTeam() {
            
        try {
            ConnectionDataBase connectionDb = new ConnectionDataBase();
            Connection connection = connectionDb.getConexao();
            String insercaoSQL = "INSERT INTO turma (semestre, ano, identrega) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insercaoSQL);
            preparedStatement.setInt(1, semester);
            preparedStatement.setInt(2, year);
            preparedStatement.setInt(3, idIssue);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            System.out.println("Dados inseridos com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}