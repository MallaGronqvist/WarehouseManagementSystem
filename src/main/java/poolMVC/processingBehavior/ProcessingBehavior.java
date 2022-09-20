package poolMVC.processingBehavior;

import product.InventoryDataItem;

import java.util.List;
import java.util.Scanner;

public interface ProcessingBehavior {

    void processOption(String input, List<? extends InventoryDataItem> data)
            throws NumberFormatException, NullPointerException;

    default String readUserInput() {
        Scanner keyboard = new Scanner(System.in);
        return keyboard.nextLine();
    }
}
