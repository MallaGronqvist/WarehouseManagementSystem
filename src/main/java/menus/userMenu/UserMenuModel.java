package menus.userMenu;

import menus.menuCommons.MenuModel;
import users.Admin;
import users.Cashier;
import users.Manager;
import users.User;

import java.util.List;

public class UserMenuModel extends MenuModel {
    private User user;

    public UserMenuModel(User user) {
        this.user = user;

        // get these directly from user?
        if(user instanceof Cashier){
            menuOptions = List.of("Register sold item", "View 'soon out of stock' products / Place order", "Sign out");
        }
        if(user instanceof Manager){
            menuOptions = List.of("Review inventory / Register sold item", "Review order list");
        }
        if(user instanceof Admin){
            menuOptions = List.of("Review inventory / Register sold item", "Sign out");
        }
    }

    @Override
    protected void processOption(int selectedOption) throws IndexOutOfBoundsException {
        user.processMenuOption(selectedOption);

        new UserMenu(user);
    }

}
