package libraryapp.ui.button.user;

import libraryapp.service.Service;
import libraryapp.service.UserCardService;
import libraryapp.service.util.UserInput;
import libraryapp.ui.button.Button;
import libraryapp.ui.button.MenuCommand;

import java.util.UUID;

/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author: Anton Gorbovyi
 * @version: 12.05.2024
 **/
public class ReopenUserCard  extends Button implements MenuCommand {

    public ReopenUserCard(Service service) {
        super.put(service.getClass().getSimpleName(), service);
    }


    @Override
    public void executeCommand() {
        Integer userId = UserInput.getInt("Enter reader ID: ");
        UserCardService userCardService = (UserCardService) super.getService(UserCardService.class.getSimpleName());
        userCardService.reopenUserCard(userId);
    }

    @Override
    public String getMenuName() {
        return "Reopen reader's card";
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}