package org.example;

import javax.swing.plaf.nimbus.State;
import java.net.ConnectException;
import java.security.spec.ECField;
import java.sql.*;

public class DBFunctions {
    public Connection connectToDb(String dbName, String user, String password) {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbName, user, password);
            if(connection != null) {
                System.out.println("connection has been established");
            } else {
                System.out.println("connection has failed");
            }
        } catch(Exception e) {
            System.out.println("not connected");
        }
        return connection;
    }

    public void createTable(Connection connection, String table_name) {
        Statement statement;
        try {
           String query = "create table " + table_name + "(empid SERIAL, name varchar(200), address varchar(200), primary key(empid));";
           statement = connection.createStatement();
           statement.executeUpdate(query);
            System.out.println("Table created");
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public void insert_row(Connection connection, String tableName, String name, String address) {
        Statement statement;
        try {
            String query = String.format("insert into %s(name, address) values('%s', '%s');", tableName, name, address);
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("data inserted successfully");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void read_data(Connection connection, String tableName){
        Statement statement;
        ResultSet resultSet;
        try{
            String query = String.format("select * from %s;", tableName);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                System.out.print(resultSet.getString("empid") + " ");
                System.out.print(resultSet.getString("name") + " ");
                System.out.println(resultSet.getString("address") + " ");
            }
        } catch(Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

    public void update_name(Connection connection, String tableName, int id, String newName) {
        Statement statement;
        try {
            String query = String.format("update %s set name = '%s' where empid = %s;", tableName, newName, id);
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("updated successfully");
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

    public void search_by_name(Connection connection, String tableName, String name) {
        Statement statement;
        ResultSet resultSet;
        try{
            String query = String.format("select * from %s where name = '%s'", tableName, name);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int numberOfColumns = resultSetMetaData.getColumnCount();
            while(resultSet.next()) {
                for(int i = 1; i <= numberOfColumns; i++) {
                    System.out.print(resultSet.getString(i) + " ");
                }
                System.out.println();
            }
        } catch(Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

    public void delete_row_by_name(Connection connection, String table_name, String name) {
        Statement statement;
        try{
            String query = String.format("delete from %s where name = '%s'", table_name, name);
            statement = connection.createStatement();
            statement.executeQuery(query);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public void delete_table(Connection connection, String table_name) {
        Statement statement;
        try {
            String query = String.format("drop table %s", table_name);
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table deleted");
        } catch(Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

}


