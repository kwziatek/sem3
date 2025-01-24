package org.example;

import java.sql.Connection;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        DbFunctions dbFunctions = new DbFunctions();
        Connection connection = dbFunctions.connectToDb("clinic", "postgres", "blossom");

        //dbFunctions.deleteTables(connection);
        dbFunctions.createAppointment(connection, "2024-10-24 09:30:00", "2", 30);
    }
}