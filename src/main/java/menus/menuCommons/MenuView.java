package menus.menuCommons;

import utils.ViewPrinter;

import java.util.List;

public class MenuView {
    public MenuView(String title, List<String> menuOptions) {
        ViewPrinter.clearConsole();

        displayTitle(title);

        ViewPrinter.listOptions(menuOptions);

        requestUserInput();
    }

    private static void displayTitle(String title) {
        System.out.println(title);
        System.out.println();
    }

    public void requestUserInput() {
        System.out.print("Enter your choice and press enter: ");
    }

    public void printInvalidOption() {
        System.out.println("You entered an invalid option.");
    }
}
