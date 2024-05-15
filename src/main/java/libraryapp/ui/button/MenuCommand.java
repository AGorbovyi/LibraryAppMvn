package libraryapp.ui.button;

/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author: Anton Gorbovyi
 * @version: 12.54.2024
 **/
public interface MenuCommand {

    void executeCommand();

    String getMenuName();

    boolean shouldExit();

}
