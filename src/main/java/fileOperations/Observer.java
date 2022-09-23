package fileOperations;

import inventoryData.InventoryDataItem;

import java.util.List;

public interface Observer {
    void update(List<? extends InventoryDataItem> data);
}
