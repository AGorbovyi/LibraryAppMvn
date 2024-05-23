package libraryapp.ui.button.book;

import libraryapp.entity.Book;
import libraryapp.service.BookService;
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
public class FindById extends Button  implements MenuCommand {

    public FindById(Service service) {
        super.put(service.getClass().getSimpleName(), service);
    }

    @Override
    public void executeCommand() {
        int id = UserInput.getInt("Enter book ID: ");
        BookService bookService = (BookService) super.getService(BookService.class.getSimpleName());
        Book book = bookService.findByCatalogNumber(id);
        if (book != null) {
            System.out.println(book);
        } else{
            System.out.println("No book with this ID found");
        }
    }

    @Override
    public String getMenuName() {
        return "Find by ID";
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
