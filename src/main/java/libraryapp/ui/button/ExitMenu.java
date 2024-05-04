package libraryapp.ui.button;

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
