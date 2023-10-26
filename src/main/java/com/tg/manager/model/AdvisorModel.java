package com.tg.manager.model;
import com.tg.manager.model.connection.ConnectionDataBase;
import lombok.Data;
import lombok.ToString;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

@Data
@ToString
public class AdvisorModel {
    private Integer id;
    private String fatecEmail;
    private String name;

    private static void addAdvisor(String name, String fatecEmail) {
        try {
            ConnectionDataBase connectionDb = new ConnectionDataBase();
            Connection connection = connectionDb.getConexao();
            String insercaoSQL = "INSERT INTO orientador (nome, email_fatec) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insercaoSQL);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, fatecEmail);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            //connection.close();
            System.out.println("Dados inseridos com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Set<AdvisorModel> getSubmit()  {
        try {
            ConnectionDataBase connectionDb = new ConnectionDataBase();
            Connection connection = connectionDb.getConexao();
            Statement statementDb = connection.createStatement();
            ResultSet result = statementDb.executeQuery("SELECT * from orientador");
            Set<AdvisorModel> AdvsiorList = new HashSet<>();
            while (result.next()) {
                AdvisorModel advisor = new AdvisorModel();
                Integer idAdvisor = result.getInt("id");
                advisor.setId(idAdvisor);
                String email = result.getString("email_fatec");
                advisor.setFatecEmail(email);
                String name = result.getString("nome");
                advisor.setName(name);
                AdvsiorList.add(advisor);
            }
            return AdvsiorList;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static boolean validatorAdvisorEmail(String name, String fatecEmail){
        if (fatecEmail.indexOf("@") != -1) {
            addAdvisor(name, fatecEmail);
            return true;
        }
        throw new RuntimeException("Email Invalid");
    }
}
