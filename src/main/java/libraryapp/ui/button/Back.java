package libraryapp.ui.button;

import libraryapp.service.Service;
import libraryapp.ui.IMenu;

/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author: Anton Gorbovyi
 * @version: 12.05.2024
 **/
public class Back extends Button implements MenuCommand {

    private final IMenu menu;
    public Back(Service service, IMenu menu) {
        super.put(service.getClass().getSimpleName(), service);
        this.menu=menu;
    }
    @Override
    public void executeCommand() {
        menu.startMenu();
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
