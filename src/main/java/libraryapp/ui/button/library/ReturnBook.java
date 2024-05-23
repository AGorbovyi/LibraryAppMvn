package libraryapp.ui.button.library;

import libraryapp.service.LibraryService;
import libraryapp.service.Service;
import libraryapp.service.util.UserInput;
import libraryapp.ui.button.Button;
import libraryapp.ui.button.MenuCommand;

/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author: Anton Gorbovyi
 * @version: 12.05.2024
 **/
public class ReturnBook extends Button  implements MenuCommand {

    public ReturnBook(Service service) {
        super.put(service.getClass().getSimpleName(), service);
    }

    @Override
    public void executeCommand() {
        Integer bookId = UserInput.getInt("Book catalog number for return to the library: ");
        LibraryService libraryService = (LibraryService) super.getService(LibraryService.class.getSimpleName());
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
