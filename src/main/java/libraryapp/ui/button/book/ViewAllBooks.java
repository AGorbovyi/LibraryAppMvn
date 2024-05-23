package libraryapp.ui.button.book;

import libraryapp.service.BookService;
import libraryapp.service.Service;
import libraryapp.ui.button.Button;
import libraryapp.ui.button.MenuCommand;

/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author: Anton Gorbovyi
 * @version: 12.05.2024
 **/
public class ViewAllBooks extends Button  implements MenuCommand {

    private BookService service;
    public ViewAllBooks(Service service) {
        this.service= (BookService) service;
    }

    @Override
    public void executeCommand() {
        service.printCatalog();
    }

    @Override
    public String getMenuName() {
        return "View all books in catalog";
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
