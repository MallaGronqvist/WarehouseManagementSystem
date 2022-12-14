package inventoryDataDisplay.dataProcessing;

import inventoryData.InventoryDataItem;
import utils.DisplayHelper;

import java.util.List;

public class DisplayInventoryDataItem implements ProcessingBehavior {
    private List<? extends InventoryDataItem> items;
    private InventoryDataItem selectedItem;

    public void processData(String input, List<? extends InventoryDataItem> data) throws NumberFormatException, NullPointerException {
        this.items = data;

        int selectedId = Integer.parseInt(input);
        this.selectedItem = getItem(selectedId);

        displayItem();

        DisplayHelper.waitForEnter();
    }

    private void displayItem() {
        DisplayHelper.clearConsole();
        DisplayHelper.displayHeader("Item details");
        List<String> headers = selectedItem.getHeaders();
        List<String> displayValues = selectedItem.getDisplayValues();

        for (int index = 0; index < headers.size(); index++) {
            DisplayHelper.displayText(headers.get(index) + ": " + displayValues.get(index));
        //    DisplayHelper.displayText(displayValues.get(index));
        }
    }

    private InventoryDataItem getItem(int selectedId) throws NullPointerException {
        InventoryDataItem result = items.stream()
                .filter(item -> item.getId() == selectedId)
                .findAny().orElse(null);

        if (result == null) {
            throw new NullPointerException();
        }

        return result;
    }
}
