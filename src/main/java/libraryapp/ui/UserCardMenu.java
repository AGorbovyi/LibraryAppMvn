package libraryapp.ui;

import libraryapp.service.Service;
import libraryapp.service.UserCardService;
import libraryapp.ui.button.Back;
import libraryapp.ui.button.ExitMenu;
import libraryapp.ui.button.MenuCommand;
import libraryapp.ui.button.user.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author: Anton Gorbovyi
 * @version: 12.05.2024
 **/
public class UserCardMenu implements IMenu{

    private List<MenuCommand> commands;
    private final String menuName;
    private IMenu previous_menu;

    public UserCardMenu(HashMap<String, Service> services, IMenu menu) {
        this.menuName=this.getClass().getSimpleName();
        AddUserCard addUserCard = new AddUserCard(services.get(UserCardService.class.getSimpleName()));
        ViewAllUserCards viewAllUserCards = new ViewAllUserCards(services.get(UserCardService.class.getSimpleName()));
        FindUserCardById findUserCardById = new FindUserCardById(services.get(UserCardService.class.getSimpleName()));
        FindUserCardByName findUserCardByNames = new FindUserCardByName(services.get(UserCardService.class.getSimpleName()));
        CloseUserCard closeUserCard = new CloseUserCard(services.get(UserCardService.class.getSimpleName()));
        ReopenUserCard reopenCard = new ReopenUserCard(services.get(UserCardService.class.getSimpleName()));
        Back back = new Back(services.get(UserCardService.class.getSimpleName()), menu);
        ExitMenu exitMenu=new ExitMenu();
        var menuCommands = new ArrayList<MenuCommand>();
        menuCommands.add(null);
        menuCommands.add(addUserCard);
        menuCommands.add(viewAllUserCards);
        menuCommands.add(findUserCardById);
        menuCommands.add(findUserCardByNames);
        menuCommands.add(closeUserCard);
        menuCommands.add(reopenCard);
        menuCommands.add(back);
        menuCommands.add(exitMenu);
        this.commands = menuCommands;
    }

    public void startMenu(){
        Scanner scanner = new Scanner(System.in);
        boolean exitRequested = false;

        while (!exitRequested) {
            for (int i = 1; i < commands.size(); i++) {
                System.out.println("[" + i + "] " + commands.get(i).getMenuName());
            }
            System.out.println("Please make your choice: ");

            int userInput = scanner.nextInt();
            if (userInput < 0 || userInput >= commands.size()) {
                System.out.println("Invalid option, please try again.");
            }
            else {
                MenuCommand command = commands.get(userInput);
                command.executeCommand();
                exitRequested = command.shouldExit();
            }
        }
    }
    @Override
    public IMenu getMenu(String name) {
        if(this.getMenuName().equals(name))
            return this;
        return null;
    }

    @Override
    public String getMenuName() {
        return this.menuName;
    }
}
