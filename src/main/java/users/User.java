package users;

import menus.mainMenu.MainMenu;
import menus.userMenu.UserMenu;
import inventoryDataDisplay.dataProcessing.PlaceOrder;
import inventoryDataDisplay.InventoryDataMenu;
import inventoryData.product.ProductPool;
import inventoryDataDisplay.dataProcessing.DisplayInventoryDataItem;

import java.util.List;

public abstract class User {

    List<String> actions;

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

    protected void viewSoonOutOfStockProducts(){
        new InventoryDataMenu(ProductPool.getSoonOutOfStockProducts(), new PlaceOrder());
    }

    protected void navigateToMainMenu(){
        new MainMenu();
    }
}
