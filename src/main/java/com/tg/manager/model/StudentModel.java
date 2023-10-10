package com.tg.manager.model;
// Using JDBC
import com.tg.manager.model.connection.ConnectionDataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class StudentModel {
    private String name;
    private String email;
    private String fatecEmail;
    private Integer advisorId;
    private Integer teamId;

    public void addStudent(String name, String email, String fatecEmail, Integer advisorId, Integer teamId ) {
        try {
            ConnectionDataBase connectionDb = new ConnectionDataBase();
            Connection connection = connectionDb.getConexao();
            String insercaoSQL = "INSERT INTO aluno (nome, email, email_fatec, idOrientador, idturma) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insercaoSQL);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, fatecEmail);
            preparedStatement.setInt(4, advisorId);
            preparedStatement.setInt(5, teamId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            System.out.println("Dados inseridos com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

    

