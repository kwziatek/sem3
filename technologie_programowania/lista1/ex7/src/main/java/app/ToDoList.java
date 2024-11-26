package app;

import java.util.ArrayList;

public class ToDoList {
    private ArrayList<Task> tasks;
    private String name;

    public ToDoList(String name){
        tasks = new ArrayList<>();
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void addTask(Task task){
        tasks.add(task);
    }

    public Task getTask(int index){
        return tasks.get(index);
    }

    public void removeTask(int  index){
        tasks.remove(index);
    }

    public void printTasks(){
        for(Task task: tasks){
            System.out.println(tasks.indexOf(task) + ". " + task);
        }
    }
}
