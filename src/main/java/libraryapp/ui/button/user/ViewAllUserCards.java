package libraryapp.ui.button.user;

import libraryapp.service.UserCardService;
import libraryapp.ui.button.MenuCommand;

public class ViewAllUserCards implements MenuCommand {

    private final UserCardService userCardService;

    public ViewAllUserCards(UserCardService bookCatalogService) {
        this.userCardService = bookCatalogService;
    }

    @Override
    public void executeCommand() {
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

