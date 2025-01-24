package org.example;

import org.example.UI.DataAdminUI;
import org.example.UI.DoctorUI;
import org.example.UI.PatientUI;
import org.example.UI.UserUI;

import java.util.Scanner;

public class LoggingInMenu {
    Scanner scanner;
    public LoggingInMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start() {
        logIn();
    }

    public void logIn() {
        while(true) {
            UserUI user = identifyTheUser();
            if(user == null) {
                break;
            }
            user.start();
        }
    }

    public UserUI identifyTheUser() {
        printUsersSet();
        String c = scanner.next();
        while(!c.equals("p") && !(c.equals("d")) && !(c.equals("a")) && !(c.equals("x"))) {
            System.out.println("not correct user type");
            c = scanner.next();
        }
        return switch (c) {
            case "p" -> new PatientUI();
            case "d" -> new DoctorUI();
            case "a" -> new DataAdminUI();
            default -> null;
        };
    }

    public void printUsersSet() {
        System.out.println("if you are a patient press p");
        System.out.println("if you are a doctor press d");
        System.out.println("if you are a data admin press a");
        System.out.println("if u want to leave press x");
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LoggingInMenu loggingInMenu = new LoggingInMenu(scanner);
        loggingInMenu.start();
    }
}
