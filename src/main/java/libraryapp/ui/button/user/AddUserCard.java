package libraryapp.ui.button.user;

import libraryapp.service.Service;
import libraryapp.service.UserCardService;
import libraryapp.service.util.UserInput;
import libraryapp.ui.button.Button;
import libraryapp.ui.button.MenuCommand;

/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author: Anton Gorbovyi
 * @version: 12.05.2024
 **/
public class AddUserCard extends Button  implements MenuCommand {

    public AddUserCard(Service service) {
        super.put(service.getClass().getSimpleName(), service);
    }


    @Override
    public void executeCommand() {
        String userName = UserInput.getText("Enter reader's first name: ");
        String userSurname = UserInput.getText("Enter reader's last name: ");
        UserCardService userCardService = (UserCardService) super.getService(UserCardService.class.getSimpleName());
        userCardService.addNewUserCard(userName, userSurname);
    }

    @Override
    public String getMenuName() {
        return "Add reader card";
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
