package inventoryDataDisplay;

import inventoryData.InventoryDataItem;
import inventoryDataDisplay.dataProcessing.ProcessingBehavior;
import utils.DisplayHelper;

import java.util.List;

public class PoolModel {
    private final List<? extends InventoryDataItem> data;
    private final ProcessingBehavior processor;

    public PoolModel(List<? extends InventoryDataItem> data, ProcessingBehavior processor) {

        this.data = data;
        this.processor = processor;
    }

    public List<? extends InventoryDataItem> getData() {
        return data;
    }

    public void processOption(String input) throws NumberFormatException, NullPointerException {

        DisplayHelper.navigateToUserMenu(input);

        processor.processData(input, data);
    }
}
