package com.tg.manager.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.tg.manager.model.connection.ConnectionDataBase;

public class TeamModel {
    private Integer halfYear;
    private Integer year;
    private Integer idTeam;
    private Integer idStudent;
    private Integer idTG;

    public void addTeam(Integer halfYear, Integer year, Integer idTeam, Integer idStudent, Integer idTG) {
            
        try {
            ConnectionDataBase connectionDb = new ConnectionDataBase();
            Connection connection = connectionDb.getConexao();
            String insercaoSQL = "INSERT INTO tg (halfYear, year, idTeam, idStudent, idTG) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insercaoSQL);
            preparedStatement.setInt(1, halfYear);
            preparedStatement.setInt(2, year);
            preparedStatement.setInt(3, idTeam);
            preparedStatement.setInt(4, idStudent);
            preparedStatement.setInt(4, idTG);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            System.out.println("Dados inseridos com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}