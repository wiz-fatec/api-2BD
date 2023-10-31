package com.tg.manager.model;
import com.tg.manager.model.connection.ConnectionDataBase;
import com.tg.manager.utils.EmailValidator;
import lombok.Data;
import lombok.ToString;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

@Data
@ToString
public class TGModel {
    private  Integer id;
    private String description;
    private String type;
    private String problem;
    private String enterprise;
    private String discipline;
    private Integer idStudent;

    public static void addTG(String description, String type, String problem, String enterprise, String discipline, Integer idStudent) {
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
            System.out.println("Dados inseridos com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  Set<TGModel> getSubmit() throws SQLException {
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

    public static void validatorTG(String description, String typeTg, String problem, String enterprise, String discipline, String emailStudent){
        EmailValidator.validatorEmail(emailStudent);
        Integer idStudent = findIdStudent(emailStudent);
        addTG(description, typeTg, problem, enterprise, discipline, idStudent);
    }


    private static Integer findIdStudent(String emailStudent){

        for(StudentModel advisorModel : StudentModel.getSubmit()){

            if(advisorModel.getFatecEmail().contains(emailStudent)){

                return  advisorModel.getId();
            }
        }
        throw new RuntimeException("Student e-mail not exist");
    }
}