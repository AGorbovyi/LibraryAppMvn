package libraryapp.ui.button.book;

import libraryapp.entity.Book;
import libraryapp.service.BookCatalogService;
import libraryapp.service.util.UserInput;
import libraryapp.ui.button.MenuCommand;

import java.util.List;

public class FindByAuthor implements MenuCommand {

    private final BookCatalogService bookCatalogService;

    public FindByAuthor(BookCatalogService bookCatalogService) {
        this.bookCatalogService = bookCatalogService;
    }

    @Override
    public void executeCommand() {
        String author = UserInput.getText("Enter author: ");
        List<Book> books = bookCatalogService.findBookByAuthor(author);
        if (books.size() > 0) {
            for (Book book : books) {
                System.out.println(book);
            }
        } else{
            System.out.println("No book with this author found");
        }
    }

    @Override
    public String getMenuName() {
        return "Find by Author";
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}

