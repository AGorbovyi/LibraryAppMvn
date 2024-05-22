package libraryapp;

import libraryapp.repository.BookCatalogRepository;
import libraryapp.repository.CrudRepository;
import libraryapp.repository.UserCardRepository;
import libraryapp.service.BookCatalogService;
import libraryapp.service.LibraryService;
import libraryapp.service.Service;
import libraryapp.service.UserCardService;
import libraryapp.ui.*;

import java.util.HashMap;

/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author: Anton Gorbovyi
 * @version: 12.05.2024
 **/

public class LibraryApp {

    public static void main(String[] args) {
        // define db name
        final String SQLITE_DB_NAME = "jdbc:sqlite:C:/temp/java_library.db";

        BookCatalogRepository bookCatalogRepository = new BookCatalogRepository();
        bookCatalogRepository.init();
        UserCardRepository userCardRepository = new UserCardRepository();
        userCardRepository.init();
        HashMap<String, CrudRepository> repositories = new HashMap<>();
        repositories.put(bookCatalogRepository.getClass().getSimpleName(), bookCatalogRepository);
        repositories.put(userCardRepository.getClass().getSimpleName(),userCardRepository);

        var menu = getAdminMenu(repositories);

        while (true){
            menu.get(AdminMenu.class.getSimpleName()).startMenu();
        }

    }

    private static HashMap<String, IMenu> getAdminMenu(HashMap<String, CrudRepository> repositories) {
        BookCatalogService bookCatalogService = new BookCatalogService(repositories);
        UserCardService userCardService = new UserCardService(repositories);
        LibraryService libraryService = new LibraryService(repositories);
        HashMap<String, Service> services = new HashMap<>();
        services.put(bookCatalogService.getClass().getSimpleName(), bookCatalogService);
        services.put(userCardService.getClass().getSimpleName(), userCardService);
        services.put(libraryService.getClass().getSimpleName(), libraryService);
        HashMap<String, IMenu> menus = getStringIMenuHashMap(services);
        return menus;
    }

    private static HashMap<String, IMenu> getStringIMenuHashMap(HashMap<String, Service> services) {
        HashMap<String, IMenu> menus = new HashMap<>();
        AdminMenu adminMenu = new AdminMenu(services);
        BookMenu bookMenu = new BookMenu(services, adminMenu);
        LibraryMenu libraryMenu=new LibraryMenu(services, adminMenu);
        UserCardMenu userCardMenu=new UserCardMenu(services, adminMenu);
        FindBookMenu findBookMenu = new FindBookMenu(services, bookMenu);
        menus.put(adminMenu.getClass().getSimpleName(), adminMenu);
        menus.put(bookMenu.getClass().getSimpleName(), bookMenu);
        menus.put(userCardMenu.getClass().getSimpleName(), userCardMenu);
        menus.put(libraryMenu.getClass().getSimpleName(), libraryMenu);
        menus.put(findBookMenu.getClass().getSimpleName(), findBookMenu);
        return menus;
    }

}
