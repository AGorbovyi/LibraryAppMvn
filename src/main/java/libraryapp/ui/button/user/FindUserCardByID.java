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

public class FindUserCardByID implements MenuCommand {

    private final UserCardService userCardService;

    public FindUserCardByID(UserCardService userCardService) {
        this.userCardService = userCardService;
    }


    @Override
    public void executeCommand() {
        int userId = UserInput.getInt("Enter reader ID: ");
        userCardService.findUserCardById(userId);
    }

    @Override
    public String getMenuName() {
        return "Find reader card by ID";
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}