package libraryapp;

//import libraryapp.repository.collection.BookCatalogRepository;
//import libraryapp.repository.collection.CrudRepository;
//import libraryapp.repository.collection.UserCardRepository;

import libraryapp.repository.db.CrudRepository;
import libraryapp.repository.db.BookRepository;
import libraryapp.repository.db.BookInfoRepository;
import libraryapp.repository.db.UserRepository;
import libraryapp.repository.db.UserCardRepository;
import libraryapp.service.BookService;
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
        final String SQLITE_DB_NAME = "jdbc:sqlite:C:/LibraryAppDB/java_library.db";

        BookRepository bookRepository = new BookRepository();
        bookRepository.init();
        BookInfoRepository bookInfoRepository = new BookInfoRepository();
        bookInfoRepository.init();
        UserRepository userRepository = new UserRepository();
        userRepository.init();
        UserCardRepository userCardRepository = new UserCardRepository();
        userCardRepository.init();
        HashMap<String, CrudRepository> repositories = new HashMap<>();
        repositories.put(bookRepository.getClass().getSimpleName(), bookRepository);
        repositories.put(bookInfoRepository.getClass().getSimpleName(), bookInfoRepository);
        repositories.put(userRepository.getClass().getSimpleName(),userRepository);
        repositories.put(userCardRepository.getClass().getSimpleName(),userCardRepository);

        var menu = getAdminMenu(repositories);

        while (true){
            menu.get(AdminMenu.class.getSimpleName()).startMenu();
        }

    }

    private static HashMap<String, IMenu> getAdminMenu(HashMap<String, CrudRepository> repositories) {
        BookService bookService = new BookService(repositories);
        UserCardService userCardService = new UserCardService(repositories);
        LibraryService libraryService = new LibraryService(repositories);
        HashMap<String, Service> services = new HashMap<>();
        services.put(bookService.getClass().getSimpleName(), bookService);
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
