package menus.menuCommons;

import utils.MenuPrinter;

import java.util.List;

public class MenuView {
    public MenuView(String title, List<String> menuOptions) {
        MenuPrinter.clearConsole();

        System.out.println(title);
        System.out.println();

        MenuPrinter.listOptions(menuOptions);

        requestUserInput();
    }

    public void requestUserInput() {
        System.out.print("Enter your choice and press enter: ");
    }

    public void printInvalidOption() {
        System.out.println("You entered an invalid option.");
    }
}
