package poolMVC;

import menus.mainMenu.MainMenu;

import java.util.Scanner;

public class PoolController {
    private PoolModel model;
    private PoolView view;

    public PoolController(PoolModel model, PoolView view) {
        this.model = model;
        this.view = view;
    }

    public void readInput() {
        Scanner keyboard = new Scanner(System.in);
        String input = keyboard.nextLine();

        // Not sure that this is ok...
        if (input.equalsIgnoreCase("x")){
            navigateToMainMenu();
        }

        try {

            model.processOption(input);

        } catch (NumberFormatException | NullPointerException exception) {
            view.printInvalidOption();
            view.requestInput();
            readInput();
        }
    }

    private void navigateToMainMenu() {
        new MainMenu();
    }
}
