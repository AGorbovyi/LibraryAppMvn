package libraryapp.ui.button;

public interface MenuCommand {

    void executeCommand();

    String getMenuName();

    boolean shouldExit();

}
