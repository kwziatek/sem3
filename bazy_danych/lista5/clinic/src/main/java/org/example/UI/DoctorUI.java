package org.example.UI;

import java.sql.Connection;

public class DoctorUI implements UserUI{
    Connection connection;
    int id;
    @Override
    public void start() {
        connection = dbFuncions.connectToDb("clinic", "postgres", "blossom");
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
            String outcome = dbFuncions.checkLogInData(connection, "doctor", login, password);
            String[] words = outcome.split(" ");
            if(words[0].equals("logged")) {
                System.out.println(words[0] + " " + words[1]);
                id = Integer.parseInt(words[2]);
                enterMenu();
            } else {
                System.out.println(outcome);
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
                case "w":
                    writeOutPrescription();
                    break;
                case "sa":
                    showAppointments();
                    break;
            }
        }
    }

    private void printPossibleActions() {
        System.out.println("press x to exit");
        System.out.println("press c to change state of an appointment");
        System.out.println("press w to write out a prescription");
        System.out.println("type sa to see your appointments");
        System.out.println("type sp to your prescriptions");
    }

    private void writeOutPrescription() {
        System.out.printf("");
    }

    public void showAppointments() {
        dbFuncions.showAppointmentsDoctor(connection, id);
    }

}
