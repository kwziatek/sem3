package app;

import java.util.Scanner;

public class LibraryInterface {
    private final Scanner scanner;
    private final Library lib;

    public LibraryInterface(final Scanner scanner, final Library lib) {
        this.scanner = scanner;
        this.lib = lib;
    }
    public void start() {
        label:
        while (true) {
            print();
            String command = scanner.nextLine();
            switch (command) {
                case "c": {
                    System.out.println("type: title, author and publication year separated by comas");
                    String data = scanner.nextLine();
                    String[] split = data.split(", ");
                    lib.createNewCopy(split[0], split[1], Integer.parseInt(split[2]));
                    break;
                }
                case "u": {
                    System.out.println("type: name and last name separated by comas");
                    String data = scanner.nextLine();
                    String[] split = data.split(", ");
                    lib.addNewUser(split[0], split[1]);
                    break;
                }
                case "coc": {
                    System.out.println("type: title, author, user Id separated by comas");
                    String data = scanner.nextLine();
                    String[] split = data.split(", ");
                    lib.checkOutCopy(split[0], split[1], Integer.parseInt(split[2]));
                    break;
                }
                case "r": {
                    System.out.println("type: title, author, publication year, bookId separated by comas");
                    String data = scanner.nextLine();
                    String[] split = data.split(", ");
                    lib.returnCopy(split[0], split[1], Integer.parseInt(split[2]), Integer.parseInt(split[3]));
                    break;
                }
                case "pb":
                    lib.printBooksInLibrary();
                    break;
                case "pcob":
                    lib.printCheckedOutBooks();
                    break;
                case "pu":
                    lib.printUsers();
                    break;
                case "q":
                    break label;
                default:
                    System.out.println("Unknown command");
                    break;
            }
        }

    }

    public static void print() {
        System.out.println("commands:");
        System.out.println("create a new copy: c");
        System.out.println("add a new user: u");
        System.out.println("check out a copy: coc");
        System.out.println("return copy: r");
        System.out.println("print list of books in library: pb");
        System.out.println("print list of checked out books: pcob");
        System.out.println("print list of users: pu");
        System.out.println("exit: q");
    }
}
