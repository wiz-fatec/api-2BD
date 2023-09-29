package com.tg.manager.model.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDataBase {
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String user = "paulo";
    private static final String password = "admin123";
    private static Connection conn;

    public static Connection getConexao(){
        try {
            if (conn == null) {
                Class.forName("org.postgresql.Driver");
                System.out.println("Banco conectado");
                conn = DriverManager.getConnection(url, user, password);
                return  conn;
            }else{
                return conn;
            }
        } catch (SQLException e){
            System.out.println("Erro: Falha na conexão com o banco de dados.");
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e){
            System.out.println("Erro: Driver JDBC não encontrado.");
            e.printStackTrace();
            return null;
        }

    }

}
