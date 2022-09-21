package menus.userMenu;

import menus.menuCommons.MenuModel;
import users.User;

public class UserMenuModel extends MenuModel {
    private User user;

    public UserMenuModel(User user) {
        this.user = user;

        menuOptions = user.getActions();
    }

    @Override
    protected void processOption(int selectedOption) throws IndexOutOfBoundsException {

        user.performAction(selectedOption);
    }

}
