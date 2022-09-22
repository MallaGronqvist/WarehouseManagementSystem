package inventoryDataDisplay;

import inventoryDataDisplay.processingBehavior.ProcessingBehavior;
import inventoryData.InventoryDataItem;

import java.util.List;

public class InventoryDataMenu {
    public InventoryDataMenu(List<? extends InventoryDataItem> data, ProcessingBehavior processor) {
        PoolModel model = new PoolModel(data, processor);
        PoolView view = new PoolView(model.getData());
        PoolController controller = new PoolController(model, view);

        controller.readInput();
    }
}
