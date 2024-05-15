package libraryapp.ui;

/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author: Anton Gorbovyi
 * @version: 12.05.2024
 **/

public interface IMenu {
    public void startMenu();
    public IMenu getMenu(String name);
    public String getMenuName();
}
