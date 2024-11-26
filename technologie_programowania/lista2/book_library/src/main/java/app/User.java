package app;

import java.util.Objects;

//High Cohesion - opisuje użytkownika
public class User {
    private final String name;
    private final String lastName;
    private final int userId;

    public User(final String name, final String lastName, final int userId) {
        this.name = name;
        this.lastName = lastName;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(name, user.name) && Objects.equals(lastName, user.lastName);
    }

    public int getUserId() {
        return userId;
    }

    public String toString() {
        return this.name + " " + this.lastName + ": " + this.userId;
    }

}
