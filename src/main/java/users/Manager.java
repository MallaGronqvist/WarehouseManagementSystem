package users;

import order.OrderPool;
import poolMVC.PoolDisplayer;
import poolMVC.processingBehavior.ReviewOrder;

public class Manager extends User{
    @Override
    public void processMenuOption(int selectedOption) throws IndexOutOfBoundsException {
        switch (selectedOption) {
            case 1 -> super.viewProductPool();
            case 2 -> viewOrderList();
            case 3 -> super.navigateToMainMenu();
            //     case 2 -> // user.doSomething()
            //     case 3 -> // user.findYourShoe()
            default -> throw new IndexOutOfBoundsException();
        }
    }

    private void viewOrderList() {
        new PoolDisplayer(OrderPool.getAllOrders(), new ReviewOrder());
    }
}
