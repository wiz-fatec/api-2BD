package com.tg.manager;

import com.tg.manager.model.connection.ConnectionDataBase;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        ConnectionDataBase conect = new ConnectionDataBase();
        conect.getConexao();
    }
}