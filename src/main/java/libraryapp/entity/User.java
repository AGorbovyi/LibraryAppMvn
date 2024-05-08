package libraryapp.entity;

/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author Larysa Petrova
 * @version 21-Apr-24
 **/

public class User {
    private final int userId;
    private final String name;
    private final String surname;

    public User(int userId, String name, String surname) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public Integer getUserId() {
        return userId;
    }

    public User getUser() {
        return this;
    }

    public void setUserId() {
        setUserId(0);
    }

    public void setUserId(int lastUserId) {
        
    }

    public void setName(String name) {
    }

    public void setSurname(String surname) {
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

}
