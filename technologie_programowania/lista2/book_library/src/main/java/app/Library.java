package app;

import java.util.*;
//Information Expert - klasa Library ma najwięcej informacji, dlatego w niej odbywa się zarządzanie książkami i użytkownikami
//Nie musi brać nic od sąsiadów, wszystko zawiera w sobie

//High Cohesion - Library służy jedynie do zarządzania książkami i tworzenia użytkowników
//Indirection - Klasa User nie jest bezpośrednio połączona z klasą Copy, lecz przez Library
public class Library {
    private final List<Copy> copiesInLibrary;
    private final Map<Copy, Integer> checkedOutCopies;
    private final List<User> users;
    private int highestBookId;

    public Library() {
        copiesInLibrary = new LinkedList<>();
        checkedOutCopies = new HashMap<>();
        users = new LinkedList<>();
        highestBookId = -1;
    }

    public List<Copy> getCopiesInLibrary() {
        return copiesInLibrary;
    }

    public Map<Copy, Integer> getCheckedOutCopies() {
        return checkedOutCopies;
    }

    public List<User> getUsers() {
        return users;
    }

    public void createNewCopy(final String title, final String author, final int yearOfPublication) {
        copiesInLibrary.add(new Copy(title, author, yearOfPublication, ++highestBookId));   //Creator - klasa Library zawiera instancje klasy Copy
    }

    public void checkOutCopy(final String title, final String author, final int userId) {
        Iterator<Copy> it = copiesInLibrary.iterator();
        while (it.hasNext()) {
            Copy copy = it.next();
            if (copy.getAuthor().equals(author) && copy.getTitle().equals(title)) {
                checkedOutCopies.put(copy, userId);
                it.remove();
                break;
            }
        }
    }

    public void returnCopy(final String title, final String author, final int yearOfPublication, final int BookId) {
        Copy copy = new Copy(title, author, yearOfPublication, BookId);
        if (!checkedOutCopies.containsKey(copy)) {
            return;
        }
        copiesInLibrary.add(copy);
        checkedOutCopies.remove(copy);
    }

    public void addNewUser(final String name, final String lastName) {
        users.add(new User(name, lastName, highestUserid() + 1));
    }
    //Creator
    private int highestUserid() {
        int id = -1;
        for (User user: users) {
            id = Integer.max(id, user.getUserId());
        }
        return id;
    }

    public void printBooksInLibrary() {
        for (Copy copy: copiesInLibrary) {
            System.out.println(copy);
        }
    }

    public void printCheckedOutBooks() {
        for (Copy copy: checkedOutCopies.keySet()) {
            System.out.println(copy);
        }
    }

    public void printUsers() {
        for (User user: users) {
            System.out.println(user);
        }
    }
}
