package libraryapp.ui.button.user;

/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author: Anton Gorbovyi
 * @version: 22.04.2024
 **/

import libraryapp.entity.User;
import libraryapp.entity.UserCard;
import libraryapp.service.UserCardService;
import libraryapp.service.util.UserInput;
import libraryapp.ui.button.MenuCommand;

public class AddUserCard implements MenuCommand {

    private final UserCardService userCardService;

    public AddUserCard(UserCardService userCardService) {
        this.userCardService = userCardService;
    }


    @Override
    public void executeCommand() {
        int userID=UserInput.getInt("Get User ID: ");
        String userName = UserInput.getText("Get User Name: "); 
        String userLastName = UserInput.getText("Get User Last Name: ");
        User user = new User(userID, userName, userLastName);
        UserCard userCard = new UserCard(user);
        userCardService.addNewUserCard(userCard);
    }

    @Override
    public String getMenuName() {
        return "Add User Card";
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
