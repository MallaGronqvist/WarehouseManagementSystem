package users;

import inventoryData.product.ProductPool;
import inventoryDataDisplay.InventoryDataMenu;
import inventoryDataDisplay.dataProcessing.DisplayInventoryDataItem;
import inventoryDataDisplay.dataProcessing.PlaceOrder;
import menus.mainMenu.MainMenu;
import menus.userMenu.UserMenu;

import java.util.List;

public abstract class User {
    protected List<String> actions;

    public List<String> getActions() {
        return actions;
    }

    public void sessionLoop() {
        new UserMenu(this);
    }

    public abstract void performAction(int selectedOption) throws IndexOutOfBoundsException;

    protected void viewProductPool() {
        new InventoryDataMenu(ProductPool.getAllProducts(), new DisplayInventoryDataItem());
    }

    protected void viewSoonOutOfStockProducts() {
        new InventoryDataMenu(ProductPool.getSoonOutOfStockProducts(), new PlaceOrder());
    }

    protected void navigateToMainMenu() {
        new MainMenu();
    }
}
