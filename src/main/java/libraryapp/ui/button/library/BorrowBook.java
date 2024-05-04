package libraryapp.ui.button.library;

/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author: Anton Gorbovyi
 * @version: 22.04.2024
 **/

import libraryapp.service.LibraryService;
import libraryapp.service.util.UserInput;
import libraryapp.ui.button.MenuCommand;

public class BorrowBook implements MenuCommand {
    private final LibraryService libraryService;

    public BorrowBook(LibraryService service) {
        this.libraryService = service;
    }

    @Override
    public void executeCommand() {
        int bookId = UserInput.getInt("Book catalog number to be borrowed to the reader: ");
        int userId = UserInput.getInt("Card ID of the user who borrows a book: ");
        libraryService.borrowBookFromLibrary(bookId, userId);
    }

    @Override
    public String getMenuName() {
        return "Borrow Book";
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
