package users;

import menus.mainMenu.MainMenu;

public class Cashier extends User {
    @Override
    public void processMenuOption(int selectedOption) throws IndexOutOfBoundsException {
        switch (selectedOption) {
                 case 1 -> super.viewProductPool();
                 case 2 -> navigateToMainMenu();
            //     case 2 -> // user.doSomething()
            //     case 3 -> // user.findYourShoe()
            default -> throw new IndexOutOfBoundsException();
        }
    }
}
