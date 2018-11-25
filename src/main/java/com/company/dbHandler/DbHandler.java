package com.company.dbHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbHandler {
    private Connection connection;

    private static DbHandler instance = new DbHandler();

    public static DbHandler getInstance(){
        return instance;
    }

    public DbHandler(){}

    public void createConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/cinema?autoReconnect=true&useSSL=false&characterEncoding=utf-8", "root", "12345");
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
