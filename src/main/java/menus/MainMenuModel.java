package mainMenu;

import menuCommons.MenuModel;
import printers.MenuPrinter;
import userRoles.Dietitian;
import userRoles.Person;
import userRoles.User;

import java.util.List;

public class MainMenuModel implements MenuModel {
    private final List<String> menuOptions = List.of("Sign in as user", "Sign in as dietitian");
    public List<String> getMenuOptions() {
        return menuOptions;
    }

    public void processOption(int selectedOption) throws IndexOutOfBoundsException {
        Person signedInUser;

        switch (selectedOption) {
            case 1 -> signedInUser = new User();
            case 2 -> signedInUser = new Dietitian();
            default -> throw new IndexOutOfBoundsException();
        }

        MenuPrinter.clearConsole();

        signedInUser.printBanner();
        signedInUser.sessionLoop();
    }
}
