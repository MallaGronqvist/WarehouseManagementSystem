package menus.menuCommons;

import utils.DisplayHelper;

import java.util.List;

public class MenuView {
    public MenuView(String title, List<String> menuOptions) {
        DisplayHelper.clearConsole();

        DisplayHelper.displayHeader(title);

        DisplayHelper.listOptions(menuOptions);

        requestUserInput();
    }

    public void requestUserInput() {
        System.out.print("Enter your choice and press enter: ");
    }

    public void printInvalidOption() {
        DisplayHelper.displayText("You entered an invalid option.");
    }
}
