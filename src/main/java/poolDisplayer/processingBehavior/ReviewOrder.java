package poolDisplayer.processingBehavior;

import menus.mainMenu.MainMenu;
import inventoryData.order.Order;
import inventoryData.order.OrderPool;
import inventoryData.InventoryDataItem;
import inventoryData.product.Product;
import inventoryData.product.ProductPool;
import utils.DisplayHelper;

import java.util.List;

public class ReviewOrder implements ProcessingBehavior {
    private List<? extends InventoryDataItem> orders;
    private Order selectedOrder;
    @Override
    public void processData(String input, List<? extends InventoryDataItem> data)
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
        if (!selectedOrder.isConfirmed()){
            DisplayHelper.displayText("This order hasn't been confirmed yet!");
        } else {
            Product product = selectedOrder.getProduct();
            int quantity = selectedOrder.getQuantity();
            ProductPool.addItemsToProduct(product, quantity);
            OrderPool.removeOrder(selectedOrder);
            DisplayHelper.displayText("Order was removed from order list.");
            DisplayHelper.displayText("Item quantity of ordered product was updated.");
        }
    }

    private void confirmOrder() {
        if (selectedOrder.isConfirmed()){
            DisplayHelper.displayText("Order is already confirmed.");
        } else {
            selectedOrder.setConfirmed(true);
            DisplayHelper.displayText("Order was confirmed.");
        }
    }

    private void rejectOrder() {
        if (selectedOrder.isConfirmed()){
            DisplayHelper.displayText("A confirmed order cannot be rejected anymore.");
        } else{
            OrderPool.removeOrder(selectedOrder);
            DisplayHelper.displayText("Order was deleted from the order list.");
        }
    }

    private void invalidOperation() {
        DisplayHelper.displayText("Invalid input");
        manageOrder();
    }

    private void displayOperationOptions() {
        DisplayHelper.displayHeader("Available operations");

        if (!selectedOrder.isConfirmed()){
            DisplayHelper.displayText("'C' - Confirm");
            DisplayHelper.displayText("'R' - Reject");
        }

        if (selectedOrder.isConfirmed()){
            DisplayHelper.displayText("'A' - Arrived");
        }
        DisplayHelper.displayText("'X' - Quit");
        DisplayHelper.displayText("Enter choice: ");
    }

    private void displayOrderDetails(Order selectedOrder) {
        DisplayHelper.displayHeader("Order details");
        DisplayHelper.displayText("Product: " + selectedOrder.getProduct().getName());
        DisplayHelper.displayText("Items in stock: " + selectedOrder.getProduct().getQuantity());
        DisplayHelper.displayText("Ordered quantity: " + + selectedOrder.getQuantity());
    }

    private Order getOrder(int selectedId) {
        InventoryDataItem result = orders.stream()
                .filter(order -> order.getId() == selectedId)
                .findAny().orElse(null);

        if (result == null) { throw new NullPointerException(); }

        return (Order) result;
    }
}
