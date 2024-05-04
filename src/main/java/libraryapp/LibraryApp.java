package libraryapp;

/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author: Anton Gorbovyi
 * @version: 22.04.2024
 **/

import libraryapp.repository.BookCatalogRepository;
import libraryapp.repository.UserCardRepository;
import libraryapp.service.BookCatalogService;
import libraryapp.service.LibraryService;
import libraryapp.service.UserCardService;
import libraryapp.ui.AdminMenu;

public class LibraryApp {

    public static void main(String[] args) {
        BookCatalogRepository bookCatalog = new BookCatalogRepository();
        bookCatalog.init();
        UserCardRepository userCardRepository = new UserCardRepository();
        userCardRepository.init();

        BookCatalogService bookCatalogService = new BookCatalogService(bookCatalog);
        LibraryService libraryService = new LibraryService(bookCatalog);
        UserCardService userCardService = new UserCardService(userCardRepository);

        AdminMenu adminMenu = new AdminMenu(bookCatalogService, userCardService, libraryService);

        while (true){
            adminMenu.init();
        }

    }

}
