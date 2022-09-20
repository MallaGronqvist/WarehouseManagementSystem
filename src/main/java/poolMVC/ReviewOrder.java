package poolMVC;

import product.InventoryDataItem;

import java.util.List;

public class ReviewOrder implements ProcessingBehavior{
    @Override
    public void processOption(String input, List<? extends InventoryDataItem> data) throws IndexOutOfBoundsException, NullPointerException {
        System.out.println("Confirm or reject?");
    }
}
