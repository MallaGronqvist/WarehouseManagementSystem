package inventoryDataDisplay;

import menus.mainMenu.MainMenu;
import inventoryData.order.Order;
import inventoryData.InventoryDataItem;
import inventoryData.product.Product;
import inventoryData.transaction.Transaction;
import utils.DisplayHelper;
import utils.Table;

import java.util.List;
import java.util.stream.Collectors;

public class PoolView {
    InventoryDataItem sampleItem;
    public PoolView(List<? extends InventoryDataItem> data) {
        DisplayHelper.clearConsole();

        try {
        // Get a sample item or alternatively fail if data is empty.
        sampleItem = data.get(0);

        displayTable(data);

        // sampleItem might be null if blanc space is mistakenly entered to empty data file
        } catch (IndexOutOfBoundsException | NullPointerException exception) {
            DisplayHelper.displayText("No items were found.");
            new MainMenu();
        }
    }

    private void displayTable(List<? extends InventoryDataItem> data) {
        generateTable(data);
        requestInput();
    }

    void requestInput() {
        if (sampleItem instanceof Product){
            DisplayHelper.requestInput("Enter selected product id.");
        } else if (sampleItem instanceof Transaction){
            DisplayHelper.requestInput("Enter selected transaction id.");
        } else if( sampleItem instanceof Order){
            DisplayHelper.requestInput("Enter selected order id.");
        }
    }

    private void generateTable(List<? extends InventoryDataItem> data) {
            Table table = new Table(sampleItem.getHeaders(), generateBody(data), sampleItem.getColumnWidths());

            table.displayData();
    }

    private List<List<String>> generateBody(List<? extends InventoryDataItem> data) {
        List<List<String>> body = data.stream()
                .map(InventoryDataItem::getDisplayValues)
                .collect(Collectors.toList());

        return body;
    }

    public void printInvalidOption() {
        DisplayHelper.displayText("You entered an invalid option.");
    }
}
