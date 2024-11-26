package app;

public class Task {
    private final String name;
    private String note;
    private boolean completed;

    public Task(String name){
        this.name = name;
        note = "";
        completed = false;
    }

    public String getName(){
        return this.name;
    }

    public void setNote(String newNote){
        this.note = newNote;
    }

    public String getNote(){
        return this.note;
    }

    public void setCompleted(){
        completed = !completed;
    }

    public String toString(){
        String ifCompleted = "";
        if(!this.completed){
            ifCompleted = "not";
        }
        return this.name + ", (note) " + this.note + "\n" + ifCompleted + " completed";
    }
}
