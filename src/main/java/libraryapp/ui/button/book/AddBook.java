package libraryapp.ui.button.book;

/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author: Anton Gorbovyi
 * @version: 22.04.2024
 **/

import libraryapp.service.BookCatalogService;
import libraryapp.service.util.UserInput;
import libraryapp.ui.button.MenuCommand;

public class AddBook implements MenuCommand {

    private final BookCatalogService bookCatalogService;

    public AddBook(BookCatalogService bookCatalogService) {
        this.bookCatalogService = bookCatalogService;
    }

    @Override
    public void executeCommand() {
        String author = UserInput.getText("Author: ");
        String bookTitle = UserInput.getText("Titel: ");
        String genre = UserInput.getText("Genre: ");
        String publisher = UserInput.getText("Publisher: ");
        int id = bookCatalogService.addBook(author, bookTitle, genre, publisher);
        System.out.println("Book added under catalog number: " + id);
    }

    @Override
    public String getMenuName() {
        return "Add new book";
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
