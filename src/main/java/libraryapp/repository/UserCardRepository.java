package libraryapp.repository;

import libraryapp.entity.User;
import libraryapp.entity.UserCard;

import java.util.*;

/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author Larysa Petrova
 * @version 21-Apr-24
 **/

public class UserCardRepository implements CrudRepository<UUID, UserCard> {
    private Map<UUID, UserCard> userCards;

    public UserCardRepository() {
        this.userCards = new HashMap<>();
    }

    @Override
    public void put(UserCard value) {
        userCards.put(value.getUserId(), value);
    }

    @Override
    public UserCard get(UUID key) {
        for (UserCard card : userCards.values()) {
            if (card.getUserId().equals(key)) {
                return card;
            }
        }
        return null;
    }

    @Override
    public void remove(UserCard value) {
        userCards.remove(value);
    }

    @Override
    public Iterable<UserCard> values() {
        return userCards.values();
    }

    @Override
    public void init() {
        List<User> usersList = new ArrayList<>(List.of(
                new User("Anton", "Gorbovyi"),
                new User("Halyna", "Potyvaieve"),
                new User("Yaroslav", "Boiko"),
                new User("Larysa", "Petrova"),
                new User("Andrey", "Shishkov")
        ));
        for (User user : usersList) {
            UserCard card = new UserCard(user);
            this.put(card);
        }
    }
}


