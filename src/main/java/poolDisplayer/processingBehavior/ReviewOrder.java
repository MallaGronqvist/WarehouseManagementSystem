package poolDisplayer.processingBehavior;

import menus.mainMenu.MainMenu;
import inventoryData.order.Order;
import inventoryData.order.OrderPool;
import inventoryData.InventoryDataItem;
import inventoryData.product.Product;
import inventoryData.product.ProductPool;

import java.util.List;

public class ReviewOrder implements ProcessingBehavior {
    private List<? extends InventoryDataItem> orders;
    private Order selectedOrder;
    @Override
    public void processOption(String input, List<? extends InventoryDataItem> data)
            throws NumberFormatException, NullPointerException {
        this.orders = data;
        int selectedId = Integer.parseInt(input);
        this.selectedOrder = getOrder(selectedId);

        displayOrderDetails(selectedOrder);

        manageOrder();
    }

    private void manageOrder() {
        displayOperationOptions();
        String answer = readUserInput();
        answer = answer.toUpperCase();

        if(answer.equalsIgnoreCase("X")){
            new MainMenu();
        }
        switch (answer){
            case "A" -> markOrderAsArrived();
            case "C" -> confirmOrder();
            case "R" -> rejectOrder();
            default -> invalidOperation();
        }

    }

    private void markOrderAsArrived() {
        if(!selectedOrder.isConfirmed()){
            System.out.println("This order hasn't been confirmed yet!");
        } else {
            Product product = selectedOrder.getProduct();
            int quantity = selectedOrder.getQuantity();
            ProductPool.addItemsToProduct(product, quantity);
            OrderPool.removeOrder(selectedOrder);
            System.out.println("Order was removed from order list.");
            System.out.println("Item quantity of ordered product was updated.");
        }
    }

    private void confirmOrder() {
        if(selectedOrder.isConfirmed()){
            System.out.println("Order is already confirmed.");
        } else {
            selectedOrder.setConfirmed(true);
            System.out.println("Order was confirmed.");
        }
    }

    private void rejectOrder() {
        if(selectedOrder.isConfirmed()){
            System.out.println("A confirmed order cannot be rejected anymore.");
        } else{
            OrderPool.removeOrder(selectedOrder);
            System.out.println("Order was deleted from the order list.");
        }
    }

    private void invalidOperation() {
        System.out.println("Invalid input");
        manageOrder();
    }

    private void displayOperationOptions() {
        System.out.println("*** Available operations ***");

        if(!selectedOrder.isConfirmed()){
            System.out.println("'C' - Confirm");
            System.out.println("'R' - Reject");
        }

        if(selectedOrder.isConfirmed()){
            System.out.println("'A' - Arrived");
        }
        System.out.println("'X' - Quit");
        System.out.print("Enter choice: ");
    }

    private void displayOrderDetails(Order selectedOrder) {
        System.out.println("*** Order details ***");
        System.out.println("Product: " + selectedOrder.getProduct().getName());
        System.out.println("Items in stock: " + selectedOrder.getProduct().getQuantity());
        System.out.println("Ordered quantity: " + + selectedOrder.getQuantity());
        System.out.println();
    }

    private Order getOrder(int selectedId) {
        InventoryDataItem result = orders.stream()
                .filter(order -> order.getId() == selectedId)
                .findAny().orElse(null);

        if (result == null) { throw new NullPointerException(); }

        return (Order) result;
    }
}
