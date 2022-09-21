package users;

import menus.mainMenu.MainMenu;
import menus.userMenu.UserMenu;
import poolDisplayer.processingBehavior.PlaceOrder;
import poolDisplayer.PoolDisplayer;
import inventoryData.product.ProductPool;
import poolDisplayer.processingBehavior.ViewInventoryDataItem;

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

        new PoolDisplayer(ProductPool.getAllProducts(), new ViewInventoryDataItem());
    }

    protected void viewSoonOutOfStockProducts(){
        new PoolDisplayer(ProductPool.getSoonOutOfStockProducts(), new PlaceOrder());
    }

    protected void navigateToMainMenu(){
        new MainMenu();
    }
}
