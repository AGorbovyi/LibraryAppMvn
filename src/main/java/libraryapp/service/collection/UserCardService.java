package libraryapp.service.collection;

import libraryapp.entity.Book;
import libraryapp.entity.User;
import libraryapp.entity.UserCard;
import libraryapp.repository.collection.CrudRepository;
import libraryapp.repository.collection.UserCardRepository;

import java.util.HashMap;

/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author Larysa Petrova
 * @version 17-Apr-24
 */

public class UserCardService extends Service<CrudRepository, String, UserCardService> implements IService<CrudRepository, String, UserCardService> {

    public UserCardService(HashMap<String, CrudRepository> repository) {
        super(repository);
    }

    public void addNewUserCard(String userName, String userSurname) {
        User user = new User(userName, userSurname);
        UserCard userCard = new UserCard(user);
        UserCardRepository repo = (UserCardRepository) super.getRepository(UserCardRepository.class.getSimpleName());
        repo.put(userCard);
        System.out.println("Reader card added: " + userCard);
    }

    public UserCard findUserCardByName(String name) {
        UserCardRepository repo = (UserCardRepository) super.getRepository(UserCardRepository.class.getSimpleName());
        for (UserCard userCard : repo.values())
            if (userCard.getUser().getFirstName().equalsIgnoreCase(name)) {
                System.out.println("Reader card found");
                return userCard;
            }
        System.out.println("Reader card with name \"" + name + "\" not found!");
        return null;
    }

    public UserCard findUserCardById(Integer userId) {
        UserCardRepository repo = (UserCardRepository) super.getRepository(UserCardRepository.class.getSimpleName());
        UserCard userCard = repo.get(userId);
        if (userCard != null) {
            System.out.println("Reader card is found! " + userCard);
        } else {
            System.out.println("Reader card with ID " + userId + " not found");
        }
        return userCard;
    }

    public boolean closeUserCard(Integer userId) {
        UserCardRepository repo = (UserCardRepository) super.getRepository(UserCardRepository.class.getSimpleName());
        UserCard userCard = repo.get(userId);
        if (userCard != null) {
            userCard.closeCard();
            return true;
        } else {
            System.out.println("Reader card with ID " + userId + " not found.");
            return false;
        }
    }

    public void reopenUserCard(Integer userId) {
        UserCardRepository repo = (UserCardRepository) super.getRepository(UserCardRepository.class.getSimpleName());
        UserCard userCard = repo.get(userId);
        if (userCard != null) {
            userCard.reopenCard();
            System.out.println("Reader card with ID " + userId + " reopened.");
        } else {
            System.out.println("Reader card with ID " + userId + " not found.");
        }
    }

    public User findUserByBook(Book book) {
        UserCardRepository repo = (UserCardRepository) super.getRepository(UserCardRepository.class.getSimpleName());
        for (UserCard userCard : repo.values()) {
            if (userCard.getUserBookList().contains(book)) {
                return userCard.getUser();
            }
        }
        return null;
    }

    public void print() {
        UserCardRepository repo = (UserCardRepository) super.getRepository(UserCardRepository.class.getSimpleName());
        repo.values().forEach(System.out::println);
    }
}
