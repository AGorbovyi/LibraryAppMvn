package libraryapp.ui;

import libraryapp.service.Service;
import libraryapp.service.util.UserInput;
import libraryapp.ui.button.ExitMenu;

import java.util.HashMap;

/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author: Anton Gorbovyi
 * @version: 12.05.2024
 **/
public class AdminMenu implements IMenu{

    ExitMenu exitMenu;
    private HashMap<String,Service> services;
    private final String menuName;

    public AdminMenu(HashMap<String, Service> services) {
        this.services=services;
        this.exitMenu = new ExitMenu();
        menuName = this.getClass().getSimpleName();
    }

    public void startMenu () {

        System.out.println("=====================");
        System.out.println("**** App menu: ****");
        System.out.println("=====================");
        System.out.println("[1] Book menu");
        System.out.println("[2] Reader menu");
        System.out.println("[3] Library menu");
        System.out.println("[4] " + this.exitMenu.getMenuName());
        System.out.println("=====================");

        int menuItem = UserInput.getInt("Please select menu item: ");

        switch (menuItem) {
            case 1:
                BookMenu bookMenu = new BookMenu(services, this);
                bookMenu.startMenu();
                break;
            case 2:
                UserCardMenu userCardMenu = new UserCardMenu(services, this);
                userCardMenu.startMenu();
                break;
            case 3:
                LibraryMenu libraryMenu = new LibraryMenu(services, this);
                libraryMenu.startMenu();
                break;
            case 4:
                this.exitMenu.executeCommand();
                break;
            default:
                System.out.println("Invalid menu item, please try again!");
        }
    }

    @Override
    public IMenu getMenu(String name) {
        if (getMenuName().equals(name)) {
            return this;
        }
        return null;
    }

    @Override
    public String getMenuName() {
        return this.menuName;
    }
}
