package libraryapp.ui.button.user;

import libraryapp.service.UserCardService;
import libraryapp.service.util.UserInput;
import libraryapp.ui.button.MenuCommand;

public class ReopenUserCard implements MenuCommand {

    private final UserCardService userCardService;

    public ReopenUserCard(UserCardService userCardService) {
        this.userCardService = userCardService;
    }


    @Override
    public void executeCommand() {
        int userID=UserInput.getInt("Get User ID: ");
        userCardService.reopenUserCard(userID);
    }

    @Override
    public String getMenuName() {
        return "Reopen Reader's Card";
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}