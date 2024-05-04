package libraryapp.ui.button.book;

import libraryapp.service.BookCatalogService;
import libraryapp.service.util.UserInput;
import libraryapp.ui.button.MenuCommand;

public class RemoveBook implements MenuCommand {

    private final BookCatalogService bookCatalogService;

    public RemoveBook(BookCatalogService bookCatalogService) {
        this.bookCatalogService = bookCatalogService;
    }

    @Override
    public void executeCommand() {
        int bookCatalogNumberRemove = UserInput.getInt("Enter book catalog number: ");
        boolean remove = bookCatalogService.removeBook(bookCatalogNumberRemove);
        if (remove) {
            System.out.println("Book was removed from catalog");
        } else {
            System.out.println("Book was not removed");
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
