package users;

import menus.userMenu.UserMenu;
import inventoryData.order.OrderPool;
import poolDisplayer.PoolDisplayer;
import poolDisplayer.processingBehavior.ReviewOrder;

import java.util.List;

public class Manager extends User{

    public Manager() {
        super.actions = List.of(
                "Review inventory",
                "Review order list",
                "Sign out");
    }

    @Override
    public void performAction(int selectedOption) throws IndexOutOfBoundsException {
        switch (selectedOption) {
            case 1 -> super.viewProductPool();
            case 2 -> viewOrderList();
            case 3 -> super.navigateToMainMenu();
            default -> throw new IndexOutOfBoundsException();
        }
        new UserMenu(this);
    }

    private void viewOrderList() {
        new PoolDisplayer(OrderPool.getAllOrders(), new ReviewOrder());
    }
}
