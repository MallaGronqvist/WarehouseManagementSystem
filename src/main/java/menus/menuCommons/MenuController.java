package menus.menuCommons;

import java.util.Scanner;

public class MenuController {
    private final MenuModel model;
    private final MenuView view;

    public MenuController(MenuModel model, MenuView view) {
        this.model = model;
        this.view = view;
    }

    public void readUserInput() {
        Scanner keyboard = new Scanner(System.in);
        String input = keyboard.nextLine();

        try {
            int selectedOption = Integer.parseInt(input);

            model.processOption(selectedOption);

        } catch (NumberFormatException | IndexOutOfBoundsException exception) {
            view.printInvalidOption();
            view.requestUserInput();
            readUserInput();
        }
    }
}
