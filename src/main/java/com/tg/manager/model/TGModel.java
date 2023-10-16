package com.tg.manager.model;
import com.tg.manager.model.connection.ConnectionDataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class TGModel {
    private String description;
    private String type;
    private String problem;
    private String enterprise;
    private String discipline;
    private Integer idStudent;
    public void addTG(String description, String type, String problem, String enterprise, String discipline, Integer idStudent) {
        try {
            ConnectionDataBase connectionDb = new ConnectionDataBase();
            Connection connection = connectionDb.getConexao();
            String insercaoSQL = "INSERT INTO tg (descricao, tipo, problema, empresa, disciplina, idAluno) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insercaoSQL);
            preparedStatement.setString(1, description);
            preparedStatement.setString(2, type);
            preparedStatement.setString(3, problem);
            preparedStatement.setString(4, enterprise);
             preparedStatement.setString(5, discipline);
            preparedStatement.setInt(6, idStudent);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            System.out.println("Dados inseridos com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}