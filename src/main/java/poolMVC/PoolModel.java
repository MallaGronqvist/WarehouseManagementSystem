package poolMVC;

import product.InventoryDataItem;

import java.util.List;

public class PoolModel {

    private List<? extends InventoryDataItem> data;
    private ProcessingBehavior processor;

    public PoolModel(List<? extends InventoryDataItem> data, ProcessingBehavior processor) {

        this.data = data;
        this.processor = processor;
    }

    public List<? extends InventoryDataItem> getData() {
        return data;
    }

    public void processOption(String input) throws NumberFormatException, NullPointerException{


        processor.processOption(input, data);
    }
}
