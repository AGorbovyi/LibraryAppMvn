package libraryapp.ui.button.user;

/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author: Anton Gorbovyi
 * @version: 22.04.2024
 **/

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
        String userName = UserInput.getText("Get User Name: ");
        String userSurname = UserInput.getText("Get User Last Name: ");
        int userId = userCardService.addNewUserCard(userName, userSurname);
        System.out.println("Book added under catalog number: " + userId);
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
