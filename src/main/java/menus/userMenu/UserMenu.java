package menus.userMenu;

import menus.menuCommons.MenuController;
import menus.menuCommons.MenuView;
import users.User;

public class UserMenu {
    User user;
    public UserMenu(User user) {
        this.user = user;
        UserMenuModel model = new UserMenuModel(user);
        MenuView view = new MenuView("*** Available Options ***", model.getMenuOptions());
        MenuController controller = new MenuController(model, view);

        controller.readUserInput();
    }
}
