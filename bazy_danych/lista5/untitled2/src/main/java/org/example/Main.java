package org.example;

import java.sql.Connection;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        DBFunctions dbFunctions = new DBFunctions();
        Connection connection = dbFunctions.connectToDb("clinic", "postgres", "blossom");
//        dbFunctions.createTable(connection, "employee");
//        dbFunctions.insert_row(connection, "employee", "Kamil", "Wroclaw");
//        dbFunctions.insert_row(connection, "employee", "Karol", "Warszawa");
//        dbFunctions.insert_row(connection, "employee", "Kazimierz", "Walbrzych");
//        dbFunctions.insert_row(connection, "employee", "Krzys", "Kielce");
//        connection = dbFunctions.connectToDb("tutorialdb", "check_login_data", "login");
//        dbFunctions.search_by_name(connection, "employee", "Karol");
//        dbFunctions.update_name(connection, "employee", 3, "Katarzyna");
//        dbFunctions.read_data(connection, "employee");
//        dbFunctions.delete_row_by_name(connection, "employee", "Kamil");

    }
}