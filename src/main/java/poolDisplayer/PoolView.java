package poolDisplayer;

import menus.mainMenu.MainMenu;
import inventoryData.order.Order;
import inventoryData.InventoryDataItem;
import inventoryData.product.Product;
import inventoryData.transaction.Transaction;
import utils.Table;

import java.util.List;
import java.util.stream.Collectors;

public class PoolView {
    InventoryDataItem sampleItem;
    public PoolView(List<? extends InventoryDataItem> data) {
        try {
        // Get a sample item or alternatively fail if data is empty.
        sampleItem = data.get(0);

        // sampleItem might be null if blanc space is mistakenly entered to empty data file
        } catch (IndexOutOfBoundsException | NullPointerException exception) {
            System.out.println("No items were found.");
            new MainMenu();
        }

        displayTable(data);
    }

    private void displayTable(List<? extends InventoryDataItem> data) {
        generateTable(data);
        requestInput();
    }

    void requestInput() {
        if (sampleItem instanceof Product){
            System.out.print("Enter selected product id, " +
                    "or 'X' to exit: ");
        } else if (sampleItem instanceof Transaction){
            System.out.print("Enter selected transaction id, " +
                    "or 'X' to exit: ");
        } else if( sampleItem instanceof Order){
            System.out.print("Enter selected order id," +
                    "or 'X' to exit: ");
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
        System.out.println("You entered an invalid option.");
    }
}
