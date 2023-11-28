package com.tg.manager.model;

import com.tg.manager.model.connection.ConnectionDataBase;
import lombok.Data;
import lombok.ToString;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private String model;

    private  static void addSubmit(String description, Date initialDate, Date finalDate, Integer idTeam, String model) {

        try {
            ConnectionDataBase connectionDb = new ConnectionDataBase();
            Connection connection = connectionDb.getConexao();
            String insercaoSQL = "INSERT INTO entrega (descricao, data_inicial, data_final, idTurma, modelo) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insercaoSQL);
            preparedStatement.setString(1, description);
            preparedStatement.setDate(2, initialDate);
            preparedStatement.setDate(3, finalDate);
            preparedStatement.setInt(4, idTeam);
            preparedStatement.setString(5, model);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Dados inseridos com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String deleteSubmit(Integer idSubmit){
        try {
            ConnectionDataBase connectionDb = new ConnectionDataBase();
            Connection connection = connectionDb.getConexao();
            String query = "DELETE  FROM entrega WHERE id = ?";
            PreparedStatement statementDb = connection.prepareStatement(query);
            statementDb.setInt(1, idSubmit);
            int rowsAffected = statementDb.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public  static Set<SubmitModel> getSubmit()  {
        try {    
            ConnectionDataBase connectionDb = new ConnectionDataBase();
            Connection connection = connectionDb.getConexao();
            Statement statementDb = connection.createStatement();
            ResultSet result = statementDb.executeQuery("SELECT * from entrega");
            Set<SubmitModel> submitList = new HashSet<>();
            while (result.next()) {
                SubmitModel submit = new SubmitModel();
                Integer idSubmit = result.getInt("id");
                submit.setId(idSubmit);
                String description = result.getString("descricao");
                submit.setDescription(description);
                Date initialDate = result.getDate("data_inicial");
                submit.setInitialDate(initialDate);
                Date finalDate = result.getDate("data_final");
                submit.setFinalDate(finalDate);
                Integer idTurma = result.getInt("idTurma");
                submit.setIdTeam(idTurma);
                String model = result.getString("modelo");
                submit.setModel(model);
                submitList.add(submit);
            }
            return submitList;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public  static String getSubmitId(Integer submit)  {
        try {
            ConnectionDataBase connectionDb = new ConnectionDataBase();
            Connection connection = connectionDb.getConexao();
            String query = "SELECT descricao FROM entrega WHERE id = ?";
            PreparedStatement statementDb = connection.prepareStatement(query);
            statementDb.setInt(1, submit);
            ResultSet result = statementDb.executeQuery();
            while (result.next()) {
                String description= result.getString("descricao");
                return description;
            }
            result.close();
            statementDb.close();
            // connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return null;
    }

    public static Set<SubmitModel> filterSubmit(int idTeam, String modelAct){
        try {
            ConnectionDataBase connectionDb = new ConnectionDataBase();
            Connection connection = connectionDb.getConexao();
            String query = "SELECT * FROM entrega WHERE idturma = ? AND modelo = ?";
            PreparedStatement statementDb = connection.prepareStatement(query);
            statementDb.setInt(1, idTeam);
            statementDb.setString(2, modelAct);
            ResultSet result = statementDb.executeQuery();
            Set<SubmitModel> listId = new HashSet<>();
            while (result.next()) {
                SubmitModel todo = new SubmitModel();
                Integer id = result.getInt("id");
                todo.setId(id);
                String description = result.getString("descricao");
                todo.setDescription(description);
                Date date = result.getDate("data_inicial");
                todo.setInitialDate(date);
                Date dateFinal = result.getDate("data_final");
                todo.setFinalDate(dateFinal);
                Integer idTeamValue = result.getInt("idturma");
                todo.setIdTeam(idTeamValue);
                String model = result.getString("modelo");
                todo.setModel(model);
                listId.add(todo);
            }
            return listId;
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return null;
    }

    public static SubmitModel filterSubmitForName(String name){
        try {
            ConnectionDataBase connectionDb = new ConnectionDataBase();
            Connection connection = connectionDb.getConexao();
            String query = "SELECT * FROM entrega WHERE descricao = ?";
            PreparedStatement statementDb = connection.prepareStatement(query);
            statementDb.setString(1, name);
            ResultSet result = statementDb.executeQuery();
            while (result.next()) {
                SubmitModel todo = new SubmitModel();
                Integer id = result.getInt("id");
                todo.setId(id);
                String description = result.getString("descricao");
                todo.setDescription(description);
                Date date = result.getDate("data_inicial");
                todo.setInitialDate(date);
                Date dateFinal = result.getDate("data_final");
                todo.setFinalDate(dateFinal);
                Integer idTeamValue = result.getInt("idturma");
                todo.setIdTeam(idTeamValue);
                String model = result.getString("modelo");
                todo.setModel(model);
                return todo;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return null;
    }

    public static void submitValidator(String description, String initialDate, String finalDate, String typeTg, String model){
        String descriptionText = description;
        Date initialDateConvert =  Date.valueOf(convertDate(initialDate));
        Date finalDateConvert =  Date.valueOf(convertDate(finalDate));
        Integer idTeam = getIdTeam(typeTg);
        addSubmit(descriptionText, initialDateConvert, finalDateConvert, idTeam, model);

    }

    private static LocalDate convertDate(String date){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dateConvert = LocalDate.parse(date, format);
        return dateConvert;
    }

    private static Integer getIdTeam(String typeTG){
        Integer semester = typeTG.equals("TG1") ? 1 : 2;
        for(TeamModel team : TeamModel.getSubmit()){
            if(team.getSemester().equals(semester)){
                return team.getId();
            }
        }
        throw new RuntimeException("Type TG not exist");
    }

    public static void deleteInDb(Integer idSubmit){
        if(!(ToDoModel.filterTodoForDelete(idSubmit).isEmpty())){
            for(Integer idForDelete : ToDoModel.filterTodoForDelete(idSubmit)){
                ToDoModel.deleteToDo(idForDelete);
            }
        }
        deleteSubmit(idSubmit);

    }

}