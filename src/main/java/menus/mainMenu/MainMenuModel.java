package menus.mainMenu;

import menus.menuCommons.MenuModel;
import users.Admin;
import users.Cashier;
import users.Manager;
import users.User;
import utils.MenuPrinter;

import java.util.List;

public class MainMenuModel extends MenuModel {

    public MainMenuModel() {
        menuOptions = List.of("Cashier", "Manager", "Admin");
    }

    public void processOption(int selectedOption) throws IndexOutOfBoundsException {
        User signedInUser;

        switch (selectedOption) {
            case 1 -> signedInUser = new Cashier();
            case 2 -> signedInUser = new Manager();
            case 3 -> signedInUser = new Admin();
            default -> throw new IndexOutOfBoundsException();
        }

        MenuPrinter.clearConsole();

        signedInUser.sessionLoop();
    }
}
