package com.tg.manager.model;
import com.tg.manager.model.connection.ConnectionDataBase;
import com.tg.manager.utils.EmailValidator;
import com.tg.manager.utils.ReportPdf;
import lombok.Data;
import lombok.ToString;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

@Data
@ToString
public class StudentModel {
    private Integer id;
    private String name;
    private String email;
    private String fatecEmail;
    private Integer advisorId;
    private Integer teamId;

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

    public static Set<StudentModel> getSubmit(){
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
        
        EmailValidator.validatorEmail(email);
        EmailValidator.validatorEmail(emailFatec);
        EmailValidator.validatorEmail(emailAdvisor);
        Integer idAdvisordb = findIdAdvisor(emailAdvisor);
        Integer idTeamdb = findTeam(typeTg);

        Set<StudentModel> studentList = getSubmit();
        for (StudentModel student : studentList) {
            if(student.getFatecEmail().equals(emailFatec)) {
                try {
                    ConnectionDataBase connectionDb = new ConnectionDataBase();
                    Connection connection = connectionDb.getConexao();
                    PreparedStatement psTgId = connection.prepareCall("SELECT tg.id AS id FROM tg INNER JOIN aluno ON aluno.id = tg.idALUNO WHERE aluno.email_fatec = ?");

                    psTgId.setString(1, emailFatec);
                    ResultSet tgResult = psTgId.executeQuery();
                    Set<Integer> tgIdList = new HashSet<>();

                    try {
                        while(tgResult.next()) {
                            tgIdList.add(tgResult.getInt("id"));
                        }

                    } catch(SQLException err) {
                        err.printStackTrace();
                    }

                    PreparedStatement psSubmitId = connection.prepareCall("SELECT valor_entrega.id AS id FROM valor_entrega INNER JOIN aluno ON aluno.id = valor_entrega.idALUNO WHERE aluno.email_fatec = ?");

                    psSubmitId.setString(1, emailFatec);
                    ResultSet psResult = psTgId.executeQuery();
                    Set<Integer> submitIdList = new HashSet<>();

                    try {
                        while(psResult.next()) {
                            submitIdList.add(psResult.getInt("id"));
                        }

                    } catch (SQLException err) {
                        err.printStackTrace();
                    }

                    for (Integer tgId : tgIdList) {
                        PreparedStatement ps = connection.prepareStatement("DELETE FROM tg WHERE id = ?");
                        ps.setInt(1, tgId);
                        ps.executeUpdate();
                    }
                    for (Integer submitId : submitIdList) {
                        PreparedStatement ps = connection.prepareStatement("DELETE FROM valor_entrega WHERE id = ?");
                        ps.setInt(1, submitId);
                        ps.executeUpdate();
                    }

                    PreparedStatement ps = connection.prepareStatement("DELETE FROM aluno WHERE id = ?");
                    ps.setInt(1, student.getId());
                    ps.executeUpdate();

                    System.out.println("Done!");

                    // connection.close();

                } catch (SQLException err) {
                    err.printStackTrace();
                }
            }
        }
        if((idAdvisordb!= -1)) {
            addStudent(nameStudent, email, emailFatec, idAdvisordb, idTeamdb);
        }
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

    public  String getTypeTg(Integer student){
        String typeTg = TGModel.getTypeTg(student);
        String descriptionTg = TGModel.getModelTg(student);
        String descriptionTranslate = DisplayTableModel.descriptionTg(descriptionTg);
        return  typeTg + " - " + descriptionTranslate;
    }

    public  String getAdvisor(Integer advisor){
        String nameAdvisor = AdvisorModel.filterIdAdvisor(advisor).getName();
        return nameAdvisor;
    }


    public  String getAdvisorFatecEmail(Integer advisor){
        String advisorFatecEmail = AdvisorModel.filterIdAdvisor(advisor).getFatecEmail();
        return advisorFatecEmail;
    }

    public  Set<ToDoModel> getTodo(Integer idStudent){
        return ToDoModel.filterTodo(idStudent);
    }

    public static void  getReport(){
        ReportPdf.reportPdf(getSubmit());
    }


}