package menus.menuCommons;

import utils.ViewPrinter;

import java.util.List;

public class MenuView {
    public MenuView(String title, List<String> menuOptions) {
        ViewPrinter.clearConsole();

        System.out.println(title);
        System.out.println();

        ViewPrinter.listOptions(menuOptions);

        requestUserInput();
    }

    public void requestUserInput() {
        System.out.print("Enter your choice and press enter: ");
    }

    public void printInvalidOption() {
        System.out.println("You entered an invalid option.");
    }
}
