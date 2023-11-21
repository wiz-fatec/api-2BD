package com.tg.manager.model;

import com.tg.manager.model.connection.ConnectionDataBase;
import lombok.Data;
import lombok.ToString;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

@Data
@ToString
public class ToDoModel {
    private Integer id;
    private String feedback;
    private Double note;
    private Integer idStudent;
    private Integer idIssue;

    public static void addToDo(String feedback, Double note, Integer idStudent, Integer idIssue) {
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
                Integer toDoId = result.getInt("id");
                toDo.setId(toDoId);
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
    public static String deleteToDo(Integer idTodo){
        try {
            ConnectionDataBase connectionDb = new ConnectionDataBase();
            Connection connection = connectionDb.getConexao();
            String query = "DELETE  FROM valor_entrega WHERE id = ?";
            PreparedStatement statementDb = connection.prepareStatement(query);
            statementDb.setInt(1, idTodo);
            int rowsAffected = statementDb.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String getFeedBackToDo(Integer idStudent){
        try {
            ConnectionDataBase connectionDb = new ConnectionDataBase();
            Connection connection = connectionDb.getConexao();
            String query = "SELECT feedback FROM valor_entrega WHERE idaluno = ? ORDER BY id DESC LIMIT 1";
            PreparedStatement statementDb = connection.prepareStatement(query);
            statementDb.setInt(1, idStudent);
            ResultSet result = statementDb.executeQuery();
            while (result.next()) {
                String type= result.getString("feedback");
                return type;
            }
         } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return null;
    }

        public static String getNoteToDo(Integer idStudent){
        try {
            ConnectionDataBase connectionDb = new ConnectionDataBase();
            Connection connection = connectionDb.getConexao();
            String query = "SELECT nota FROM valor_entrega WHERE idaluno = ? ORDER BY id DESC LIMIT 1";
            PreparedStatement statementDb = connection.prepareStatement(query);
            statementDb.setInt(1, idStudent);
            ResultSet result = statementDb.executeQuery();
            while (result.next()) {
                String type= result.getString("nota");
                return type;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return null;
    }


    public static Set<Integer> filterTodoForDelete(int idSubmit){
        try {
            ConnectionDataBase connectionDb = new ConnectionDataBase();
            Connection connection = connectionDb.getConexao();
            String query = "SELECT id FROM valor_entrega WHERE identrega = ?";
            PreparedStatement statementDb = connection.prepareStatement(query);
            statementDb.setInt(1, idSubmit);
            ResultSet result = statementDb.executeQuery();
            Set<Integer> listId = new HashSet<>();
            while (result.next()) {
                String id= result.getString("id");
                listId.add(Integer.valueOf(id));
            }
            return listId;
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return null;

    }

    public static Set<ToDoModel> filterTodo(int idStudent){
        try {
            ConnectionDataBase connectionDb = new ConnectionDataBase();
            Connection connection = connectionDb.getConexao();
            String query = "SELECT * FROM valor_entrega WHERE idaluno = ?";
            PreparedStatement statementDb = connection.prepareStatement(query);
            statementDb.setInt(1, idStudent );
            ResultSet result = statementDb.executeQuery();
            Set<ToDoModel> listId = new HashSet<>();
            while (result.next()) {
                ToDoModel todo = new ToDoModel();
                Integer id = result.getInt("id");
                todo.setId(id);
                Double note = result.getDouble("nota");
                todo.setNote(note);
                String feedBack = result.getString("feedback");
                todo.setFeedback(feedBack);
                Integer idStudentTodo = result.getInt("idaluno");
                todo.setIdStudent(idStudentTodo);
                Integer idIssue= result.getInt("identrega");
                todo.setIdIssue(idIssue);
                listId.add(todo);
            }
            return listId;
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return null;

    }

    public static void toDoValidator(Integer idIssue, Integer idStudent, Double note, String feedback) throws Exception{
        Integer idIssueToDo = idIssue;
        Integer idStudentToDo =  idStudent;
        String feedbackToDo = feedback;
        Double notaTratada = note;

        if (notaTratada >= 0 && notaTratada <= 10) {

            addToDo(feedbackToDo,notaTratada,idStudentToDo,idIssueToDo);

        }else {
            throw new Exception("Note is invalid");
        }

    }
}