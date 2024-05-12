package libraryapp.ui.button.user;

import libraryapp.service.Service;
import libraryapp.service.UserCardService;
import libraryapp.ui.button.Button;
import libraryapp.ui.button.MenuCommand;

/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author: Anton Gorbovyi
 * @version: 12.05.2024
 **/
public class ViewAllUserCards extends Button  implements MenuCommand {

    public ViewAllUserCards(Service service) {
        super.put(service.getClass().getSimpleName(),service);
    }

    @Override
    public void executeCommand() {
        UserCardService userCardService = (UserCardService) super.getService(UserCardService.class.getSimpleName());
        userCardService.print();
    }

    @Override
    public String getMenuName() {
        return "View all readers of the library";
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}

