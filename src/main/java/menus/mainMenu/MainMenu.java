package menus.mainMenu;

import menus.menuCommons.MenuController;
import menus.menuCommons.MenuView;

public class MainMenu {

    public MainMenu() {

        MainMenuModel model = new MainMenuModel();
        MenuView view = new MenuView("INVENTORY SYSTEM", model.getMenuOptions());
        MenuController controller = new MenuController(model, view);

        controller.readUserInput();
    }
}
