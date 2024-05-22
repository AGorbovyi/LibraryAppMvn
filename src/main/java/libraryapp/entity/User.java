package libraryapp.entity;

/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author Larysa Petrova
 * @version 21-Apr-24
 **/

public class User {
    private final Integer userId;
    private final String firstName;
    private final String lastName;

    public User(String firstName, String lastName) {
        this.userId = null;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserFullName() {
        return getFirstName() + " " + getLastName();
    }

    public Integer getUserId() {
        return userId;
    }

    public User getUser() {
        return this;
    }

    public void setFirstName(String firstName) {
    }

    public void setLastName(String lastName) {
    }

    @Override
    public String toString() {
        return "userID=" + getUserId() +
                ", name='" + getFirstName() + '\'' +
                ", surname='" + getLastName() + '\'';
    }
}
