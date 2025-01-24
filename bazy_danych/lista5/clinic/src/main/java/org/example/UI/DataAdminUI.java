package org.example.UI;

import java.sql.Connection;

public class DataAdminUI implements UserUI{
    Connection connection;
    @Override
    public void start() {
        connection = dbFuncions.connectToDb("clinic", "dataAdmin", "dataAdmin");
        checkLogInData();
    }

    @Override
    public void checkLogInData() {
        while(true) {
            System.out.println("press c to change user type (press any other key otherwise)");
            String change = scanner.nextLine();
            if(change.equals("c")) {
                break;
            }
            System.out.println("type your login (email)");
            String login = scanner.nextLine();
            System.out.println("type your password");
            String password = scanner.nextLine();
            String outcome = dbFuncions.checkLogInData(connection, "dataAdmin", login, password); //TODO: create table for storing dataAdmins loginIn data
            System.out.println(outcome);
            if(outcome.equals("logged in")) {
                System.out.println("if entered");
                enterMenu();
            }
        }
    }

    public void enterMenu() {
        label: while(true) {
            printPossibleActions();
            String command = scanner.nextLine();
            switch (command){
                case "x":
                    break label;
                //TODO: finish Menu
            }
        }
    }

    private void printPossibleActions() {
        System.out.println("press x to exit");
//        System.out.println("press b to book an appointment");
//        System.out.println("press c to cancel an appointment");
//        System.out.println("type sa to see your appointments");
//        System.out.println("type sp to see prescriptions");
    }

}
