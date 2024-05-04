package libraryapp.entity;

/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author Larysa Petrova
 * @version 21-Apr-24
 **/

public class User {
    private int userID;
    private String name;
    private String surname;

    public User(int userID, String name, String surname) {
        this.userID = userID;
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public Integer getUserId() {
        return userID;
    }

    public User getUser() {
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

}
