package com.tg.manager.model;

import com.tg.manager.model.connection.ConnectionDataBase;
import lombok.Data;
import lombok.ToString;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.sql.Date;
import java.util.Set;

@Data
@ToString
public class SubmitModel {
    private Integer id;
    private String description;
    private Date initialDate;
    private Date finalDate;
    private Integer idTeam;

    public void addSubmit(String description, Date initialDate, Date finalDate, Integer idTeam) {

        try {
            ConnectionDataBase connectionDb = new ConnectionDataBase();
            Connection connection = connectionDb.getConexao();
            String insercaoSQL = "INSERT INTO entrega (descricao, data_inicial, data_final, idTurma) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insercaoSQL);
            preparedStatement.setString(1, description);
            preparedStatement.setDate(2, initialDate);
            preparedStatement.setDate(3, finalDate);
            preparedStatement.setInt(4, idTeam);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            //connection.close();
            System.out.println("Dados inseridos com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Set<SubmitModel> getSubmit() throws SQLException {
        try {    
            ConnectionDataBase connectionDb = new ConnectionDataBase();
            Connection connection = connectionDb.getConexao();
            Statement statementDb = connection.createStatement();
            ResultSet result = statementDb.executeQuery("SELECT * from entrega");
            Set<SubmitModel> submitList = new HashSet<>();
            while (result.next()) {
                SubmitModel submit = new SubmitModel();
                String description = result.getString("descricao");
                submit.setDescription(description);
                Date initialDate = result.getDate("data_inicial");
                submit.setInitialDate(initialDate);
                Date finalDate = result.getDate("data_final");
                submit.setFinalDate(finalDate);
                Integer idTurma = result.getInt("idTurma");
                submit.setIdTeam(idTurma);
                submitList.add(submit);
            }
            return submitList;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}