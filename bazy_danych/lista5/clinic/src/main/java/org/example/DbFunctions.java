package org.example;

import org.example.UI.PatientUI;

import java.sql.*;
import java.util.Arrays;

public class DbFunctions {
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
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return connection;
    }

    public void createTables(Connection connection) {
        Statement statement;
        try{
            String query = "create table Patient (ID SERIAL primary key , name varchar(100), surname varchar(100)," +
                    "pesel varchar(100), login varchar(100), password varchar(100));";
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("patient table has been created");

            query = "create table Specialisation (ID SERIAL primary key, name varchar(200));";
            statement.executeUpdate(query);
            System.out.println("specialisation table has been created");

            query = "create table Doctor( " +
                    "ID SERIAL primary key , " +
                    "name varchar(100), " +
                    "surname varchar(100)," +
                    "pesel varchar(100), " +
                    "specialisation_id integer references Specialisation, " +
                    "login varchar(100), " +
                    "password varchar(100));";
            statement.executeUpdate(query);
            System.out.println("doctor table has been created");

            query = "create table Room (ID Serial primary key, number int)";
            statement.executeUpdate(query);
            System.out.println("room table has been create");

            query = "create table Prescription (ID Serial primary key, prescription text," +
                    "prescription_code integer)";
            statement.executeUpdate(query);
            System.out.println("prescription table has been created");

            query = "create type took_place_state as ENUM ('patient didnt come', 'taken place', 'not taken place yet');";
            statement.executeUpdate(query);
            System.out.println("took_place_state has been added");

            query = "create table Appointment (ID Serial primary key, " +
                    "doc_id integer references Doctor," +
                    "pat_id integer references Patient," +
                    "roo_id integer references Room," +
                    "pre_id integer references Prescription," +
//                    "paid boolean not null," +
                    "took_place took_place_state);";
            statement.executeUpdate(query);
            System.out.println("appointment table has been created");
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public void deleteTables(Connection connection) {
        Statement statement;
        try {
            statement = connection.createStatement();

            String query;


            query = "drop table Specialisation";
            statement.executeUpdate(query);
            System.out.println("spec deleted");

            query = "drop table Patient";
            statement.executeUpdate(query);
            System.out.println("patient deleted");

            query = "drop table Room";
            statement.executeUpdate(query);

            query = "drop table Doctor";
            statement.executeUpdate(query);
            System.out.println("doc deleted");

            query = "drop table Prescription";
            statement.executeUpdate(query);

            query = "drop type if exists took_place_state";
            statement.executeUpdate(query);

            query = "drop table Appointment";
            statement.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public String checkLogInData(Connection connection, String tableName, String login, String password) {
        Statement statement;
        ResultSet resultSet;
        try{
            String query = String.format("select password, id from %s where login = '%s'", tableName, login);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            if(!resultSet.next()) {
                return "invalid login";
            } else {
                if(resultSet.getString(1).equals(password)) {
                    return "logged in " + resultSet.getString(2);
                }

            }
            return "wrong password";
        } catch(Exception e) {
            System.out.println(e.getStackTrace());
            return "login failed";
        }

    }

    public boolean ifExists(Connection connection, String tableName, String columnName, String searchElement) {
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("select * from %s where %s = '%s'", tableName, columnName, searchElement);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            return resultSet.next();
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return false;
        }
    }

    public void showDoctors(Connection connection, String specName) {
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("SELECT doctor.id, doctor.name, surname FROM doctor INNER JOIN specialisation AS s ON s.id = doctor.specialisation_id WHERE s.name = '%s'", specName);
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
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public void showBookedDates(Connection connection, String doc_id) {
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("SELECT date FROM appointment WHERE appointment.doc_id = '%s'", doc_id);
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
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public boolean createAppointment (Connection connection, String date, String doc_id, int pat_id) {
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("select * from room where id not in (select roo_id from appointment where date = '%s')", date);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            if(!resultSet.next()) {
                System.out.println("no empty rooms at this date");
                return false;
            }

            String roo_id = resultSet.getString(1);
            query = String.format("insert into appointment(doc_id, pat_id, roo_id, date) values ('%s', '%s', '%s', '%s')", doc_id, pat_id, roo_id, date);
            statement.executeUpdate(query);
            System.out.println("appointment booked");
            return true;
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return false;
        }
    }

    public void showAppointments(Connection connection, int pat_id) {
        try {
            String query = "SELECT d.name, d.surname, a.date, r.number " +
                    "FROM appointment AS a " +
                    "INNER JOIN doctor AS d ON d.id = a.doc_id " +
                    "INNER JOIN room AS r ON r.id = a.roo_id " +
                    "WHERE a.pat_id = ?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, pat_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String doctorName = rs.getString("name");
                String doctorSurname = rs.getString("surname");
                String appointmentDate = rs.getString("date");
                int roomNumber = rs.getInt("number");
                System.out.println("doctor: " + doctorName + " " + doctorSurname + ", date: " + appointmentDate + ", room number: " + roomNumber);
            }
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public void setCancelled(Connection connection, String date) {
        Statement statement;
        try {
            String query = String.format("update appointment set took_place = 'cancelled' where date = '%s'", date);
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("visit cancelled");
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));

        }
    }

    public void showAppointmentsDoctor(Connection connection, int doc_id) {
        try {
            String query = "SELECT p.name, p.surname, a.date, r.number " +
                    "FROM appointment AS a " +
                    "INNER JOIN patient AS p ON p.id = a.pat_id " +
                    "INNER JOIN room AS r ON r.id = a.roo_id " +
                    "WHERE a.doc_id = ?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, doc_id);
            ResultSet rs = ps.executeQuery();

            if(!rs.next()) {
                System.out.println("error");
            }

            while (rs.next()) {
                String doctorName = rs.getString("name");
                String doctorSurname = rs.getString("surname");
                String appointmentDate = rs.getString("date");
                int roomNumber = rs.getInt("number");
                System.out.println("patient: " + doctorName + " " + doctorSurname + ", date: " + appointmentDate + ", room number: " + roomNumber);
            }
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public void insertPrescription(Connection connection, String date) {
        Statement statement;
        try {
            String query = String.format("insert into prescription ( where date = '%s'", date); //TODO : finish
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("visit cancelled");
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));

        }
    }

}
