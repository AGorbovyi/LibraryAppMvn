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
    private UserCardRepository repository;

    public UserCardService(UserCardRepository repository) {
        this.repository = repository;

    }

    public void addNewUserCard(UserCard card) {
        repository.put(card);
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
            System.out.println("UserCard with ID " + userId + " not found");
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
            System.out.println("UserCard wiht Id " + userId + " reopened.");
        } else {
            System.out.println("UserCard with ID " + userId + " not found.");
        }
    }
    public User findUserByBook(Book book){
        for (UserCard userCard : repository.values()){
            if (userCard.getUserBookList().contains(book)){
                return userCard.getUser();
            }
        }
        return null;
    }
}
