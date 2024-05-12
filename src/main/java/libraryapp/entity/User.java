package libraryapp.entity;

import java.util.UUID;

/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author Larysa Petrova
 * @version 21-Apr-24
 **/

public class User {
    private final UUID userId;
    private final String name;
    private final String surname;

    public User(String name, String surname) {
        this.userId = UUID.randomUUID();
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getUserFullName() {
        return getName() + " " + getSurname();
    }

    public UUID getUserId() {
        return userId;
    }

    public User getUser() {
        return this;
    }

    public void setName(String name) {
    }

    public void setSurname(String surname) {
    }

    @Override
    public String toString() {
        return "userID=" + getUserId() +
                ", name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'';
    }
}
