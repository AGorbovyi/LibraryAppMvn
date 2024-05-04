package libraryapp.ui.button;

import libraryapp.ui.AdminMenu;
import libraryapp.ui.BookMenu;

public class Back implements MenuCommand {
    private AdminMenu adminMenu;
    private BookMenu bookMenu;

    public Back(AdminMenu adminMenu) {
        this.adminMenu = adminMenu;
    }
    public Back(BookMenu bookMenu) {
        this.bookMenu = bookMenu;
    }

    @Override
    public void executeCommand() {
        if (adminMenu == null) {
            bookMenu.startUserMenu();
        } else {
            adminMenu.init();
        }
    }

    @Override
    public String getMenuName() {
        return "Back";
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
