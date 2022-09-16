package mainMenu;

import menuCommons.MenuView;
import printers.MenuPrinter;

import java.util.List;

public class MainMenuView extends MenuView {

    public MainMenuView(List<String> menuOptions) {
        MenuPrinter.clearConsole();

        System.out.println("Welcome to Recipe Manager!");
        System.out.println();
        System.out.println("Choose your user type:");

        MenuPrinter.listOptions(menuOptions);

        requestUserInput();
    }
}
