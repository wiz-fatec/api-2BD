package com.tg.manager.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.ibatis.common.jdbc.ScriptRunner;

import com.tg.manager.model.connection.ConnectionDataBase;

public class GeneralModel {
    public void createDatabase() {    
        try {
            ConnectionDataBase connectionDb = new ConnectionDataBase();
            Connection connection = connectionDb.getConexao();
            BufferedReader reader = new BufferedReader(new FileReader(".\\src\\main\\java\\com\\tg\\manager\\model\\script\\script.sql"));
            ScriptRunner script = new ScriptRunner(connection, false, false);
            script.runScript(reader);
            connection.commit();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
