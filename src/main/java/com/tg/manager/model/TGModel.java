package com.tg.manager.model;
import com.tg.manager.model.connection.ConnectionDataBase;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class TGModel {
    private  Integer id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public Integer getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(Integer idStudent) {
        this.idStudent = idStudent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<TGModel> getSubmit() throws SQLException {
        try {
            ConnectionDataBase connectionDb = new ConnectionDataBase();
            Connection connection = connectionDb.getConexao();
            Statement statementDb = connection.createStatement();
            ResultSet result = statementDb.executeQuery("SELECT * from tg");
            Set<TGModel> tgList = new HashSet<>();
            while (result.next()) {
                TGModel tg = new TGModel();
                Integer idTg = result.getInt("id");
                tg.setId(idTg);
                String description = result.getString("descricao");
                tg.setDescription(description);
                String type = result.getString("tipo");
                tg.setType(type);
                String problem = result.getString("problema");
                tg.setProblem(problem);
                String enterprise = result.getString("empresa");
                tg.setEnterprise(enterprise);
                Integer idStudent = result.getInt("idaluno");
                tg.setIdStudent(idStudent);
                tgList.add(tg);
            }
            return tgList;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return "TGModel{" +
                "description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", problem='" + problem + '\'' +
                ", enterprise='" + enterprise + '\'' +
                ", discipline='" + discipline + '\'' +
                ", idStudent=" + idStudent +
                '}';
    }
}