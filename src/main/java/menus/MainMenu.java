package mainMenu;

import menuCommons.MenuController;

public class MainMenu {

    public MainMenu(){

        MainMenuModel model = new MainMenuModel();
        MainMenuView view = new MainMenuView(model.getMenuOptions());
        MenuController controller = new MenuController(model, view);

        controller.readUserInput();
    }
}
