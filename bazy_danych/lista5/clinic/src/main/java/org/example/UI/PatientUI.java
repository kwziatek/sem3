package org.example.UI;

import java.sql.Connection;
import java.sql.Timestamp;

public class PatientUI implements UserUI{
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
            String outcome = dbFuncions.checkLogInData(connection, "patient", login, password);
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
                case "b":
                    bookAppointment();
                    break;
                case "c":
                    cancelAppointment();
                    break;
                case "sa":
                    showAppointments();
                    break;
            }
        }
    }

    private void printPossibleActions() {
        System.out.println("press x to exit");
        System.out.println("press b to book an appointment");
        System.out.println("press c to cancel an appointment");
        System.out.println("type sa to see your appointments");
        System.out.println("type sp to see prescriptions");
        System.out.println("pay for a visit");
    }

    public void bookAppointment() {
        String specialisation;
        while(true) {
            System.out.println("What specialisation?");
            specialisation = scanner.nextLine();
            if(dbFuncions.ifExists(connection, "specialisation", "name", specialisation)) {
                break;
            }
            System.out.println("No such specialisation");
        }
        dbFuncions.showDoctors(connection, specialisation);
        String doctor;
        while(true) {
            System.out.println("What Doctor (type id)?");
            doctor = scanner.nextLine();
            if(dbFuncions.ifExists(connection, "doctor", "id", doctor)) {
                break;
            }
            System.out.println("No such id");
        }
        System.out.println("You can choose any date (from monday to friday) at any time in format x:00 or x:30, where 8 <= x <= 16");
        System.out.println("These are the dates that are already booked");
        dbFuncions.showBookedDates(connection, doctor);

        String date;
        do {
            System.out.println("What Date (type in format YYYY-MM-DD HH:MM:SS)");
            date = scanner.nextLine();
        } while (!dbFuncions.createAppointment(connection, date, doctor, id));
    }

    public void showAppointments() {
        dbFuncions.showAppointments(connection, id);
    }

    public void cancelAppointment() {
        showAppointments();
        System.out.println("Type date of the appointment to cancel");
        String date = scanner.nextLine();

        dbFuncions.setCancelled(connection, date);
    }
}
