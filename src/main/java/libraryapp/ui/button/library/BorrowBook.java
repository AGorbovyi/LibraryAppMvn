package libraryapp.ui.button.library;

import libraryapp.service.LibraryService;
import libraryapp.service.Service;
import libraryapp.service.util.UserInput;
import libraryapp.ui.button.Button;
import libraryapp.ui.button.MenuCommand;

import java.util.UUID;

/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author: Anton Gorbovyi
 * @version: 12.05.2024
 **/
public class BorrowBook extends Button  implements MenuCommand {

    public BorrowBook(Service service) {
        super.put(service.getClass().getSimpleName(), service);
    }

    @Override
    public void executeCommand() {
        Integer bookId = UserInput.getInt("Book catalog number to be borrowed to the reader: ");
        Integer userId = UserInput.getInt("Card ID of the user who borrows a book: ");
        LibraryService libraryService = (LibraryService) super.getService(LibraryService.class.getSimpleName());
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
