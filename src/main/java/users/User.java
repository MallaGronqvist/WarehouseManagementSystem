package users;

import menus.mainMenu.MainMenu;
import menus.userMenu.UserMenu;
import poolMVC.PlaceOrder;
import poolMVC.PoolDisplayer;
import poolMVC.ProcessSoldProduct;
import product.Product;
import product.ProductPool;

public abstract class User {
    public void sessionLoop() {
        new UserMenu(this);
    }

    public abstract void processMenuOption(int selectedOption) throws IndexOutOfBoundsException;

    protected void viewProductPool() {

        new PoolDisplayer(ProductPool.getAllProducts(), new ProcessSoldProduct());
    }

    protected void viewSoonOutOfStockProducts(){
        new PoolDisplayer(ProductPool.getSoonOutOfStockProducts(), new PlaceOrder());
    }

    protected void navigateToMainMenu(){
        new MainMenu();
    }
}
