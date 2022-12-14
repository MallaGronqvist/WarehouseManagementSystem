package users;


import inventoryData.order.OrderPool;
import inventoryData.transaction.TransactionPool;
import inventoryDataDisplay.InventoryDataMenu;
import inventoryDataDisplay.dataProcessing.DisplayInventoryDataItem;
import menus.userMenu.UserMenu;

import java.util.List;

public class Admin extends User {

    public Admin() {
        super.actions = List.of(
                "View Inventory",
                "View Transactions",
                "View Orders",
                "Sign out");
    }

    @Override
    public void performAction(int selectedOption) throws IndexOutOfBoundsException {
        switch (selectedOption) {
            case 1 -> super.viewProductPool();
            case 2 -> viewTransactions();
            case 3 -> viewOrders();
            case 4 -> navigateToMainMenu();
            default -> throw new IndexOutOfBoundsException();
        }
        new UserMenu(this);
    }

    private void viewTransactions() {
        new InventoryDataMenu(TransactionPool.getAllTransactions(), new DisplayInventoryDataItem());
    }

    private void viewOrders() {
        new InventoryDataMenu(OrderPool.getAllOrders(), new DisplayInventoryDataItem());
    }
}
