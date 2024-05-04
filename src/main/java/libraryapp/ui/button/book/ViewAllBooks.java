package libraryapp.ui.button.book;

import libraryapp.service.BookCatalogService;
import libraryapp.ui.button.MenuCommand;

public class ViewAllBooks implements MenuCommand {

    private final BookCatalogService bookCatalogService;

    public ViewAllBooks(BookCatalogService bookCatalogService) {
        this.bookCatalogService = bookCatalogService;
    }

    @Override
    public void executeCommand() {
        bookCatalogService.printCatalog();
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
