package libraryapp.ui.button.user;

import libraryapp.entity.UserCard;
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
public class FindUserCardByName extends Button  implements MenuCommand {

    public FindUserCardByName(Service service) {
        super.put(service.getClass().getSimpleName(), service);
        }


    @Override
    public void executeCommand() {
        String userName=UserInput.getText("Enter reader's name: ");
        UserCardService userCardService = (UserCardService) super.getService(UserCardService.class.getSimpleName());
        UserCard card =userCardService.findUserCardByName(userName);
        System.out.println(card);
    }

    @Override
    public String getMenuName() {
        return "Find reader card by name";
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}