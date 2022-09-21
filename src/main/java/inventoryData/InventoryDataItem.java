package inventoryData;

import java.util.List;

public abstract class InventoryDataItem {

    public abstract List<String> getHeaders();

    public abstract List<Integer> getColumnWidths();

    public abstract List<String> getDisplayValues();

    public abstract String getSavable();

    public abstract int getId();
}
