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

public class UpdateUserCard implements MenuCommand {

    private final UserCardService userCardService;

    public UpdateUserCard(UserCardService userCardService) {
        this.userCardService = userCardService;
    }


    @Override
    public void executeCommand() {
        String userName = UserInput.getText("Get reader name: ");
        String userSurname = UserInput.getText("Get reader last name: ");
        int userId = userCardService.addNewUserCard(userName, userSurname);
        System.out.println("Reader card was changed under id: " + userId);
    }

    @Override
    public String getMenuName() {
        return "Change reader card";
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
