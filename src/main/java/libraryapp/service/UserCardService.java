package libraryapp.service;
/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author Larysa Petrova
 * @version 17-Apr-24
 */

import libraryapp.entity.Book;
import libraryapp.entity.User;
import libraryapp.entity.UserCard;
import libraryapp.repository.UserCardRepository;

public class UserCardService {
    private final UserCardRepository repository;

    public UserCardService(UserCardRepository repository) {
        this.repository = repository;

    }

    public int addNewUserCard(String userName, String userSurname) {
        int lastUserId = repository.values().size() + 1;
        lastUserId++;
        User user = new User(lastUserId, userName, userSurname);
        UserCard userCard = new UserCard(user);
        repository.put(userCard);
        return lastUserId;
    }

    private boolean validateVarName(String varName) {
        // check variable name length
        if (varName.length() == 0) {
            System.out.println("Error: variable name is empty");
            return false;
        }
        char firstChar =varName.charAt(0);
        if (!(Character.isLetter(firstChar))){
            System.out.println("Error: variable name is invalid");
            return false;
        }
        for (int i=1;i<varName.length();i++){
            char currentChar=varName.charAt(i);
            if (Character.isLetterOrDigit(currentChar)||currentChar=='_'){
                System.out.println("Eror: variable name contains invalid characters");
                return false;
            }
        }
        return true;
    }

    public void findUserCardByName(String name) {
        boolean found = false;
        for (UserCard userCard : repository.values()) {
            if (userCard.getUser().getName().equalsIgnoreCase(name)) {
                System.out.println(userCard);
                found = true;
            }
        }
        if (!found) {
            System.out.println("User card with name \"" + name + "\"is not found!");
        }
    }

    public void findUserCardById(int userId) {
        UserCard userCard = repository.get(userId);
        if (userCard != null) {
            System.out.println("User card found! " + userCard);
        } else {
            System.out.println("User Card with ID " + userId + " not found");
        }
    }

    public boolean closeUserCard(int userId) {
        UserCard userCard = repository.get(userId);
        if (userCard != null) {
            userCard.closeCard();
            return true;
        } else {
            System.out.println("UserCard with ID " + userId + " not found.");
            return false;
        }
    }

    public void reopenUserCard(int userId) {
        UserCard userCard = repository.get(userId);
        if (userCard != null) {
            userCard.reopenCard();
            System.out.println("UserCard with Id " + userId + " reopened.");
        } else {
            System.out.println("UserCard with ID " + userId + " not found.");
        }
    }

    public User findUserByBook(Book book) {
        for (UserCard userCard : repository.values()) {
            if (userCard.getUserBookList().contains(book)) {
                return userCard.getUser();
            }
        }
        return null;
    }

    public void print() {
        repository.findAll().forEach(System.out::println);
    }

    public boolean updateUserCard(int id, String name, String surname) {
        UserCard userCard = repository.get(id);
        if (userCard != null) {
            userCard.getUser().setName(name);
            userCard.getUser().setSurname(surname);
            return true;
        }
        return false;
    }
}
