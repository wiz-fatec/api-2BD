package com.tg.manager.model;
import com.tg.manager.model.connection.ConnectionDataBase;
import lombok.Data;
import lombok.ToString;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

@Data
@ToString
public class StudentModel {
    private  Integer id;
    private String name;
    private String email;
    private String fatecEmail;
    private Integer advisorId;
    private Integer teamId;

    private static Set<String> listEmails = new HashSet<>();

    private static void addStudent(String name, String email, String fatecEmail, Integer advisorId, Integer teamId ) {
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
    public static void validator(String email, String emailFatec, String nameStudent, String emailAdvisor, String typeTg){

        validatorEmail(email);
        validatorEmail(emailFatec);
        validatorEmail(emailAdvisor);
        Integer idAdvisordb = findIdAdvisor(emailAdvisor);
        Integer idTeamdb = findTeam(typeTg);
        if(!(listEmails.contains(emailFatec)) && (idAdvisordb!= -1)){
            listEmails.add(emailFatec);
            addStudent(nameStudent, email, emailFatec, idAdvisordb, idTeamdb);
        }
    }

    private static boolean validatorEmail(String email){

        if(email.indexOf("@")!=-1){

            return true;
        }
        throw  new RuntimeException("Email invalid");
    }

    private static Integer findIdAdvisor(String emailAdvisor){

        for(AdvisorModel advisorModel : AdvisorModel.getSubmit()){

            if(advisorModel.getFatecEmail().contains(emailAdvisor)){

                return  advisorModel.getId();
            }
        }
        throw new RuntimeException("Advisor e-mail not exist");

    }

    private static Integer findTeam(String typeTg){
        if(typeTg.contains("TG1") && typeTg.contains("TG2")){
            for(TeamModel teamModel : TeamModel.getSubmit()){
                if(teamModel.getSemester() == 3)
                    return teamModel.getId();
            }
        } else if (typeTg.contains("TG1")) {
            for(TeamModel teamModel : TeamModel.getSubmit()){
                if(teamModel.getSemester() == 1)
                    return teamModel.getId();
            }

        } else if (typeTg.contains("TG2")) {
            for(TeamModel teamModel : TeamModel.getSubmit()){
                if(teamModel.getSemester() == 2)
                    return teamModel.getId();
            }

        }
        throw new RuntimeException("Team does not exist");
    }

}

    

