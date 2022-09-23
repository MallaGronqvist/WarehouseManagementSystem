package users;

import inventoryData.product.Product;
import inventoryData.product.ProductPool;
import inventoryData.transaction.Transaction;
import inventoryData.transaction.TransactionPool;
import inventoryDataDisplay.dataProcessing.ReturnItem;
import menus.userMenu.UserMenu;
import inventoryDataDisplay.InventoryDataMenu;
import inventoryDataDisplay.dataProcessing.RegisterSoldItem;
import utils.DisplayHelper;

import java.util.List;
import java.util.Scanner;

public class Cashier extends User {

    public Cashier() {
        super.actions = List.of(
                "View inventory & Register sold item",
                "View 'soon out of stock' products & Place order",
                "Return item",
                "Sign out");
    }

    @Override
    public void performAction(int selectedOption) throws IndexOutOfBoundsException {
        switch (selectedOption) {
            case 1 -> viewProductPool();
            case 2 -> super.viewSoonOutOfStockProducts();
            case 3 -> returnItem();
            case 4 -> navigateToMainMenu();
            default -> throw new IndexOutOfBoundsException();
        }
        new UserMenu(this);
    }

    private void returnItem() {
        new ReturnItem();
    }

    @Override
    protected void viewProductPool() {

        new InventoryDataMenu(ProductPool.getAllProducts(), new RegisterSoldItem());
    }
}
