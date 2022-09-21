package poolDisplayer.processingBehavior;

import inventoryData.InventoryDataItem;
import inventoryData.product.Product;

import java.util.List;

public class ViewInventoryDataItem implements ProcessingBehavior{
    private List<? extends InventoryDataItem> items;
    private InventoryDataItem selectedItem;

    public void processOption(String input, List<? extends InventoryDataItem> data) throws NumberFormatException, NullPointerException {
        this.items = data;

        int selectedId = Integer.parseInt(input);
        this.selectedItem = getItem(selectedId);

        displayItem();
    }

    private void displayItem() {
        List<String> headers = selectedItem.getHeaders();
        List<String> displayValues = selectedItem.getDisplayValues();
        for (int index = 0; index < headers.size(); index++) {
            System.out.print( headers.get(index) + ": ");
            System.out.println( displayValues.get(index));
        }
    }


    private InventoryDataItem getItem(int selectedId) throws NullPointerException {
        InventoryDataItem result = items.stream()
                .filter(item -> item.getId() == selectedId)
                .findAny().orElse(null);

        if (result == null) { throw new NullPointerException(); }

        return result;
    }
}
