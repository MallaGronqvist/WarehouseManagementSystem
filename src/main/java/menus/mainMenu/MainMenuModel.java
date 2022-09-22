package menus.mainMenu;

import menus.menuCommons.MenuModel;
import users.Admin;
import users.Cashier;
import users.Manager;
import users.User;
import utils.DisplayHelper;

import java.util.List;

public class MainMenuModel extends MenuModel {

    public MainMenuModel() {
        menuOptions = List.of("Cashier", "Manager", "Admin", "Exit");
    }

    public void processOption(int selectedOption) throws IndexOutOfBoundsException {
        User signedInUser = null;

        switch (selectedOption) {
            case 1 -> signedInUser = new Cashier();
            case 2 -> signedInUser = new Manager();
            case 3 -> signedInUser = new Admin();
            case 4 -> exit();
            default -> throw new IndexOutOfBoundsException();
        }

        DisplayHelper.setSignedInUser(signedInUser);

        signedInUser.sessionLoop();
    }

    private void exit() {

        System.exit(0);
    }
}
