package inventoryDataDisplay;

import java.util.Scanner;

public class PoolController {
    private final PoolModel model;
    private final PoolView view;

    public PoolController(PoolModel model, PoolView view) {
        this.model = model;
        this.view = view;
    }

    public void readInput() {
        Scanner keyboard = new Scanner(System.in);
        String input = keyboard.nextLine();

        try {

            model.processOption(input);

        } catch (NumberFormatException | NullPointerException exception) {
            view.printInvalidOption();
            view.requestInput();
            readInput();
        }
    }
}
