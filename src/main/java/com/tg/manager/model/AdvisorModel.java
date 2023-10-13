package com.tg.manager.model;
import com.tg.manager.model.connection.ConnectionDataBase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class AdvisorModel {
 private String fatecEmail;
 private String name;
    public void addAdvisor(String name, String fatecEmail) {
        try {
            ConnectionDataBase connectionDb = new ConnectionDataBase();
            Connection connection = connectionDb.getConexao();
            String insercaoSQL = "INSERT INTO orientador (nome, email_fatec) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insercaoSQL);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, fatecEmail);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            System.out.println("Dados inseridos com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
