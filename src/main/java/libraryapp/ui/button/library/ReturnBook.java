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

public class ReturnBook implements MenuCommand {
    private final LibraryService libraryService;

    public ReturnBook(LibraryService service) {
        this.libraryService = service;
    }

    @Override
    public void executeCommand() {
        int bookId = UserInput.getInt("Book catalog number for return to the library: ");
        libraryService.returnBookToLibrary(bookId);
    }

    @Override
    public String getMenuName() {
        return "Return Book";
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
