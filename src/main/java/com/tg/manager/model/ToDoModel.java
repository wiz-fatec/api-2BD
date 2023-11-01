package com.tg.manager.model;

import com.tg.manager.model.connection.ConnectionDataBase;
import lombok.Data;
import lombok.ToString;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

@Data
@ToString
public class ToDoModel {
    private String feedback;
    private Double note;
    private Integer idStudent;
    private Integer idIssue;

    public void addToDo(String feedback, Double note, Integer idStudent, Integer idIssue) {
        try {
            ConnectionDataBase connectionDb = new ConnectionDataBase();
            Connection connection = connectionDb.getConexao();
            String insercaoSQL = "INSERT INTO valor_entrega (nota, feedback, idaluno, identrega) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insercaoSQL);
            preparedStatement.setDouble(1, note);
            preparedStatement.setString(2, feedback);
            preparedStatement.setInt(3, idStudent);
            preparedStatement.setInt(4, idIssue);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            //connection.close();
            System.out.println("Dados inseridos com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static Set<ToDoModel> getToDo(){
        try {    
            ConnectionDataBase connectionDb = new ConnectionDataBase();
            Connection connection = connectionDb.getConexao();
            Statement statementDb = connection.createStatement();
            ResultSet result = statementDb.executeQuery("SELECT * from valor_entrega");
            Set<ToDoModel> toDoList = new HashSet<>();
            while (result.next()) {
                ToDoModel toDo = new ToDoModel();
                Double grade = result.getDouble("nota");
                toDo.setNote(grade);
                String feedback = result.getString("feedback");
                toDo.setFeedback(feedback);
                Integer studentId = result.getInt("idaluno");
                toDo.setIdStudent(studentId);
                Integer issueId = result.getInt("identrega");
                toDo.setIdIssue(issueId);
                toDoList.add(toDo);
            }
            return toDoList;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }

}
