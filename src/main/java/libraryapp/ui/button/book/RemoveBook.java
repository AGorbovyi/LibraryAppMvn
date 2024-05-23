package libraryapp.ui.button.book;

import libraryapp.service.BookCatalogService;
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
public class RemoveBook extends Button  implements MenuCommand {

    private BookCatalogService service;
    public RemoveBook(Service service) {
        this.service= (BookCatalogService) service;
    }

    @Override
    public void executeCommand() {
        int bookCatalogNumberRemove = UserInput.getInt("Enter book catalog number: ");
        boolean remove = service.removeBook(bookCatalogNumberRemove);
        if (remove) {
            System.out.println("Book was removed from catalog");
        } else {
            System.out.println("Book with catalog number" + bookCatalogNumberRemove + " was not found!");
        }
    }

    @Override
    public String getMenuName() {
        return "Remove book from catalog";
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
