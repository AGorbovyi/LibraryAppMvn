package libraryapp.entity;

import java.util.UUID;

/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author Larysa Petrova
 * @version 21-Apr-24
 **/

public class User {
    private final Integer id;
    private final String name;
    private final String surname;

    public User(String name, String surname) {
        this.id = null;
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

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return this;
    }

    public void setName(String name) {
    }

    public void setSurname(String surname) {
    }
    public void setId(Integer id) {
    }
    @Override
    public String toString() {
        return "userID=" + getId() +
                ", name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'';
    }
}
