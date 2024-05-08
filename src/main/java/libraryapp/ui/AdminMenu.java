package libraryapp.ui;

/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author: Anton Gorbovyi
 * @version: 22.04.2024
 **/

import libraryapp.service.BookCatalogService;
import libraryapp.service.LibraryService;
import libraryapp.service.UserCardService;
import libraryapp.service.util.UserInput;
import libraryapp.ui.button.Back;
import libraryapp.ui.button.ExitMenu;
import libraryapp.ui.button.MenuCommand;
import libraryapp.ui.button.book.AddBook;
import libraryapp.ui.button.book.FindBook;
import libraryapp.ui.button.book.RemoveBook;
import libraryapp.ui.button.book.ViewAllBooks;
import libraryapp.ui.button.library.BorrowBook;
import libraryapp.ui.button.library.ReturnBook;
import libraryapp.ui.button.user.*;
import libraryapp.ui.button.user.ViewAllUserCards;

import java.util.ArrayList;
import java.util.List;

public class AdminMenu {

    ExitMenu exitMenu;
    Back back;
    private List<MenuCommand> menuCommands;
    private BookCatalogService bookCatalogService;
    private UserCardService userCardService;
    private LibraryService libraryService;



    public AdminMenu(BookCatalogService bookCatalogService, UserCardService userCardService, LibraryService libraryService) {
        this.exitMenu = new ExitMenu();
        this.bookCatalogService = bookCatalogService;
        this.libraryService=libraryService;
        this.userCardService=userCardService;
        this.menuCommands = new ArrayList<>();
    }

    public void init () {

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
                AddBook addBook = new AddBook(bookCatalogService);
                ViewAllBooks viewAllBooks = new ViewAllBooks(bookCatalogService);
                FindBook findBook = new FindBook(bookCatalogService);
                RemoveBook removeBook = new RemoveBook(bookCatalogService);
                back = new Back(this);
                menuCommands.clear();
                menuCommands.add(null);
                menuCommands.add(addBook);
                menuCommands.add(viewAllBooks);
                menuCommands.add(findBook);
                menuCommands.add(removeBook);
                menuCommands.add(back);
                menuCommands.add(exitMenu);
                BookMenu bookMenu = new BookMenu(menuCommands);
                bookMenu.startUserMenu();
                break;
            case 2:
                AddUserCard addUserCard = new AddUserCard(userCardService);
                ViewAllUserCards viewAllUserCards = new ViewAllUserCards(userCardService);
                CloseUserCard closeUserCard = new CloseUserCard(userCardService);
                FindUserCardByID findUserCardById = new FindUserCardByID(userCardService);
                FindUserCardByName findUserCardByNames = new FindUserCardByName(userCardService);
                ReopenUserCard reopenCard = new ReopenUserCard(userCardService);
                back = new Back(this);
                menuCommands.clear();
                menuCommands.add(null);
                menuCommands.add(addUserCard);
                menuCommands.add(viewAllUserCards);
                menuCommands.add(closeUserCard);
                menuCommands.add(findUserCardById);
                menuCommands.add(findUserCardByNames);
                menuCommands.add(reopenCard);
                menuCommands.add(back);
                menuCommands.add(exitMenu);
                UserCardMenu userCardMenu = new UserCardMenu(menuCommands);
                userCardMenu.startUserCardMenu();
                break;
            case 3:
                BorrowBook borrow = new BorrowBook(libraryService);
                ReturnBook returnBook = new ReturnBook(libraryService);
                back = new Back(this);
                menuCommands.clear();
                menuCommands.add(null);
                menuCommands.add(borrow);
                menuCommands.add(returnBook);
                menuCommands.add(back);
                menuCommands.add(exitMenu);
                LibraryMenu libraryMenu = new LibraryMenu(menuCommands);
                libraryMenu.startLibraryMenu();
                break;
            case 4:
                this.exitMenu.executeCommand();
                break;
            default:
                System.out.println("Invalid menu item, please try again!");
        }
    }

}
