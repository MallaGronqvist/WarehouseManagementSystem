package inventoryDataDisplay.processingBehavior;

import inventoryData.InventoryDataItem;

import java.util.List;
import java.util.Scanner;

public interface ProcessingBehavior {


    void processData(String input, List<? extends InventoryDataItem> data)
            throws NumberFormatException, NullPointerException;


    default String readUserInput() {
        Scanner keyboard = new Scanner(System.in);
        return keyboard.nextLine();
    }
}
