package libraryapp.ui;

import libraryapp.service.LibraryService;
import libraryapp.service.Service;
import libraryapp.ui.button.Back;
import libraryapp.ui.button.ExitMenu;
import libraryapp.ui.button.MenuCommand;
import libraryapp.ui.button.library.BorrowBook;
import libraryapp.ui.button.library.ReturnBook;

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

public class LibraryMenu implements IMenu {
    private List<MenuCommand> commands;
    private final String menuName;

    public LibraryMenu(HashMap<String, Service> services, IMenu menu) {
        this.menuName=this.getClass().getSimpleName();
        BorrowBook borrow = new BorrowBook(services.get(LibraryService.class.getSimpleName()));
        ReturnBook returnBook = new ReturnBook(services.get(LibraryService.class.getSimpleName()));
        Back back = new Back(services.get(LibraryService.class.getSimpleName()), menu);
        ExitMenu exitMenu=new ExitMenu();
        var menuCommands = new ArrayList<MenuCommand>();
        menuCommands.add(null);
        menuCommands.add(borrow);
        menuCommands.add(returnBook);
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
