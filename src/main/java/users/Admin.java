package users;


import menus.userMenu.UserMenu;

import java.util.List;

public class Admin extends User{

    public Admin() {
        super.actions = List.of(
                "Review inventory / Register sold item",
                "Sign out");
    }

    @Override
    public void performAction(int selectedOption) throws IndexOutOfBoundsException {
        switch (selectedOption) {
            case 1 -> super.viewProductPool();
            case 2 -> navigateToMainMenu();
            default -> throw new IndexOutOfBoundsException();
        }
        new UserMenu(this);
    }
}
