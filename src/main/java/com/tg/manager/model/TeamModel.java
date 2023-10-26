package com.tg.manager.model;
import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import com.tg.manager.model.connection.ConnectionDataBase;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@ToString
public class TeamModel {
    private Integer id;
    private Integer semester;
    private Integer year;

    public static void addTeam(Integer semester, Integer year) {
        try {
            ConnectionDataBase connectionDb = new ConnectionDataBase();
            Connection connection = connectionDb.getConexao();
            String insercaoSQL = "INSERT INTO turma (semestre, ano) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insercaoSQL);
            preparedStatement.setInt(1, semester);
            preparedStatement.setInt(2, year);
            System.out.println(preparedStatement.executeUpdate());
            preparedStatement.close();
            System.out.println("Dados inseridos com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Set<TeamModel> getSubmit(){
        try {
            ConnectionDataBase connectionDb = new ConnectionDataBase();
            Connection connection = connectionDb.getConexao();
            Statement statementDb = connection.createStatement();
            ResultSet result = statementDb.executeQuery("SELECT * from turma");
            Set<TeamModel> teamList = new HashSet<>();
            while (result.next()) {
                TeamModel team = new TeamModel();
                Integer idTeam = result.getInt("id");
                team.setId(idTeam);
                Integer semester = result.getInt("semestre");
                team.setSemester(semester);
                Integer year = result.getInt("ano");
                team.setYear(year);
                teamList.add(team);
            }
            return teamList;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}