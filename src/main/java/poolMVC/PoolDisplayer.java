package poolMVC;

import poolMVC.processingBehavior.ProcessingBehavior;
import product.InventoryDataItem;

import java.util.List;

public class PoolDisplayer {

    // Ask why List< ? implements InventoryData> data doesn't work.
    public PoolDisplayer(List<? extends InventoryDataItem> data, ProcessingBehavior processor) {
        PoolModel model = new PoolModel(data, processor);
        PoolView view = new PoolView(model.getData());
        PoolController controller = new PoolController(model, view);

        controller.readInput();
    }
}
