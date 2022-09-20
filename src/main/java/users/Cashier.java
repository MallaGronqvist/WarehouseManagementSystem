package users;

import menus.mainMenu.MainMenu;

public class Cashier extends User {
    @Override
    public void processMenuOption(int selectedOption) throws IndexOutOfBoundsException {
        switch (selectedOption) {
                 case 1 -> super.viewProductPool();
                 case 2 -> super.viewSoonOutOfStockProducts();
                 case 3-> navigateToMainMenu();
            default -> throw new IndexOutOfBoundsException();
        }
    }
}
