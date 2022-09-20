package poolMVC;

import order.Order;
import product.InventoryDataItem;
import product.Product;
import transaction.Transaction;

import java.util.List;
import java.util.stream.Collectors;

public class PoolView {
    InventoryDataItem sampleItem;
    public PoolView(List<? extends InventoryDataItem> data) {
        try {
        // Fail early if data is empty.
        sampleItem = data.get(0);

        } catch (IndexOutOfBoundsException exception) {
            System.out.println("No items were found.");
            return;
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
            System.out.println("Enter selected receipt number, " +
                    "or 'X' to exit: ");
        } else if( sampleItem instanceof Order){
            System.out.print("Enter an order id to confirm or cancel order," +
                    "or 'X' to exit: ");
        }
    }

    private void generateTable(List<? extends InventoryDataItem> data) {

            Table table = new Table(sampleItem.getHeaders(), generateBody(data), sampleItem.getColumnWidths());

            table.showData();

    }

    private List<List<String>> generateBody(List<? extends InventoryDataItem> data) {
        List<List<String>> body = data.stream()
                .map(InventoryDataItem::getDisplayValues)
                .collect(Collectors.toList());

        return body;
    }

    public void printInvalidOption() {
        System.out.println("⚠️ You entered an invalid option.");
    }
}
