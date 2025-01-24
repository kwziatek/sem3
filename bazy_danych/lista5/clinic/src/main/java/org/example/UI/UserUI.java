package org.example.UI;

import org.example.DbFunctions;

import java.sql.Connection;
import java.util.Scanner;

public interface UserUI {
    void start();
    void checkLogInData();
    DbFunctions dbFuncions = new DbFunctions();
    Scanner scanner = new Scanner(System.in);
}
