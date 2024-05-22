package libraryapp.entity;

import java.util.List;

/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author Larysa Petrova
 * @version 21-Apr-24
 **/

public class User {
    private Integer id;
    private String name;
    private final String surname;
    private List<UserCard> userCards;

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
        this.name=name;
    }

    public void setSurname(String surname) {
    }
    public void setId(Integer id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "userID=" + getId() +
                ", name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'';
    }
}
