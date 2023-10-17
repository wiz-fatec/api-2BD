package com.tg.manager.model;
import com.tg.manager.model.connection.ConnectionDataBase;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class StudentModel {
    private  Integer id;
    private String name;
    private String email;
    private String fatecEmail;
    private Integer advisorId;
    private Integer teamId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFatecEmail() {
        return fatecEmail;
    }

    public void setFatecEmail(String fatecEmail) {
        this.fatecEmail = fatecEmail;
    }

    public Integer getAdvisorId() {
        return advisorId;
    }

    public void setAdvisorId(Integer advisorId) {
        this.advisorId = advisorId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    @Override
    public String toString() {
        return "StudentModel{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", fatecEmail='" + fatecEmail + '\'' +
                ", advisorId=" + advisorId +
                ", teamId=" + teamId +
                '}';
    }

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

    public Set<StudentModel> getSubmit() throws SQLException {
        try {
            ConnectionDataBase connectionDb = new ConnectionDataBase();
            Connection connection = connectionDb.getConexao();
            Statement statementDb = connection.createStatement();
            ResultSet result = statementDb.executeQuery("SELECT * from aluno");
            Set<StudentModel> studentList = new HashSet<>();
            while (result.next()) {
                StudentModel student = new StudentModel();
                Integer idStudent = result.getInt("id");
                student.setId(idStudent);
                String email = result.getString("email_fatec");
                student.setFatecEmail(email);
                String email2 = result.getString("email");
                student.setEmail(email2);
                String name = result.getString("nome");
                student.setName(name);
                Integer idAdvisor = result.getInt("idorientador");
                student.setAdvisorId(idAdvisor);
                Integer idTeam = result.getInt("idturma");
                student.setTeamId(idTeam);
                studentList.add(student);
            }
            return studentList;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}

    

