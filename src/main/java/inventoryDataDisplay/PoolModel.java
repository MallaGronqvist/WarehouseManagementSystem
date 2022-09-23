package inventoryDataDisplay;

import menus.mainMenu.MainMenu;
import inventoryDataDisplay.dataProcessing.ProcessingBehavior;
import inventoryData.InventoryDataItem;
import utils.DisplayHelper;

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

        DisplayHelper.navigateToUserMenu(input);

        processor.processData(input, data);
    }

    private void navigateToMainMenu() {
        new MainMenu();
    }
}
