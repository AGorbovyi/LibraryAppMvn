package libraryapp.ui;

import libraryapp.service.BookService;
import libraryapp.service.Service;
import libraryapp.ui.button.Back;
import libraryapp.ui.button.ExitMenu;
import libraryapp.ui.button.MenuCommand;
import libraryapp.ui.button.book.FindByAuthor;
import libraryapp.ui.button.book.FindById;
import libraryapp.ui.button.book.FindByTitle;

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
public class FindBookMenu implements IMenu{

    private final String menuName;
    private List<MenuCommand> commands;

    public FindBookMenu(HashMap<String, Service> services, IMenu menu) {
        this.menuName=this.getClass().getSimpleName();
        BookService bookService = (BookService) services.get(BookService.class.getSimpleName());
        FindByAuthor author = new FindByAuthor(bookService);
        FindByTitle title = new FindByTitle(bookService);
        FindById id = new FindById(bookService);
        Back back = new Back(bookService, menu);
        ExitMenu exit = new ExitMenu();

        List<MenuCommand> menuCommands=new ArrayList<>();

        menuCommands.add(null);
        menuCommands.add(author);
        menuCommands.add(title);
        menuCommands.add(id);
        menuCommands.add(back);
        menuCommands.add(exit);
        this.commands = menuCommands;
    }

    public void startMenu() {
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
            } else {
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
