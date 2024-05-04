package libraryapp.ui.button.book;

import libraryapp.entity.Book;
import libraryapp.service.BookCatalogService;
import libraryapp.service.util.UserInput;
import libraryapp.ui.button.MenuCommand;

public class FindById implements MenuCommand {

    private final BookCatalogService bookCatalogService;

    public FindById(BookCatalogService bookCatalogService) {
        this.bookCatalogService = bookCatalogService;
    }

    @Override
    public void executeCommand() {
        int id = UserInput.getInt("Enter book ID: ");
        Book book = bookCatalogService.findByCatalogNumber(id);
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
