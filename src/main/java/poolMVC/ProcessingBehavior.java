package poolMVC;

import product.InventoryDataItem;

import java.util.List;

public interface ProcessingBehavior {
    void processOption(String input, List<? extends InventoryDataItem> data)
            throws IndexOutOfBoundsException, NullPointerException;
}
