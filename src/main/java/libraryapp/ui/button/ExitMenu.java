package libraryapp.ui.button;

/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author: Anton Gorbovyi
 * @version: 12.05.2024
 **/

public class ExitMenu implements MenuCommand {
    @Override
    public void executeCommand() {
        System.out.println("Goodbye!");
        System.exit(0);
    }

    @Override
    public String getMenuName() {
        return "Exit App";
    }

    @Override
    public boolean shouldExit() {
        return true;
    }
}
