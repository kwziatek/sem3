package app;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    private ArrayList<ToDoList> lists;
    public UserInterface(Scanner scanner){
        this.scanner = scanner;
        lists = new ArrayList<>();
    }

    public void start(){
        while(true) {
            listOfCommandsForList();
            String command = scanner.nextLine();
            if (command.equals("t")) {
                System.out.println("type the name of new list");
                String name = scanner.nextLine();
                lists.add(new ToDoList(name));
            } else if (command.equals("c")) {
                System.out.println("type the index of desired list");
                int index = Integer.parseInt(scanner.nextLine());
                startList(lists.get(index));
            } else if (command.equals("q")) {
                break;
            } else if (command.equals("p")){
                for(ToDoList toDoList: lists){
                    System.out.println(lists.indexOf(toDoList) + ". " + toDoList.getName());
                }
            }
            else {
                System.out.println("unknown command");
            }
        }
    }

    public void startList(ToDoList list){
        while(true) {
            listOfCommandsForTask();
            String command = scanner.nextLine();
            if (command.equals("a")) {
                System.out.println("type the name of the task");
                String name = scanner.nextLine();
                list.addTask(new Task(name));
            } else if (command.equals("r")) {
                list.printTasks();
                System.out.println("type the index of the task to be deleted");
                int index = Integer.parseInt(scanner.nextLine());
                list.removeTask(index);
            } else if (command.equals("p")) {
                list.printTasks();
            } else if (command.equals("n")) {
                list.printTasks();
                System.out.println("type the index of the task, to set the note");
                int index = Integer.parseInt(scanner.nextLine());
                System.out.println("type the note");
                String note = scanner.nextLine();
                list.getTask(index).setNote(note);
            } else if (command.equals("c")){
                list.printTasks();
                System.out.println("type the index of the task, to change completeness");
                int index = Integer.parseInt(scanner.nextLine());
                list.getTask(index).setCompleted();
            } else if (command.equals("q")){
                break;
            } else{
                System.out.println("Unknown command");
            }
        }
    }

    public void listOfCommandsForList(){
        System.out.println("t - new todo list");
        System.out.println("c - choose todo list");
        System.out.println("p - print todo lists");
        System.out.println("q - to quit");
    }

    public void listOfCommandsForTask(){
        System.out.println("a - add new task");
        System.out.println("r - remove task");
        System.out.println("p - print all tasks");
        System.out.println("n - set note");
        System.out.println("c - set as (in)completed");
        System.out.println("q - go back to list menu");
    }
}
