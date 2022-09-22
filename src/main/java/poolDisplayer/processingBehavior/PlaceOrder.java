package poolDisplayer.processingBehavior;

import inventoryData.transaction.Transaction;
import inventoryData.transaction.TransactionPool;
import inventoryData.transaction.TransactionType;
import menus.mainMenu.MainMenu;
import inventoryData.order.Order;
import inventoryData.order.OrderPool;
import inventoryData.InventoryDataItem;
import inventoryData.product.Product;
import utils.DisplayHelper;

import java.util.List;

public class PlaceOrder implements ProcessingBehavior {
    List<? extends InventoryDataItem> products;
    Product selectedProduct;

    @Override
    public void processData(String input, List<? extends InventoryDataItem> data)
            throws NullPointerException, NumberFormatException {

        this.products = data;
        int selectedId = Integer.parseInt(input);
        this.selectedProduct = getProduct(selectedId);

        prepareOrder();
    }

    private void prepareOrder() {
        DisplayHelper.displayHeader("Create new order");

        boolean placeNewOrder = true;

        if (OrderPool.isAlreadyOrdered(selectedProduct)) {
            displayOrderedQuantity(selectedProduct);

            placeNewOrder = orderAnyway();
        }

        if (placeNewOrder) {
            int quantity = requestQuantity();

            createOrder(selectedProduct, quantity);
            System.out.println("An order was created successfully.");
        } else {
            new MainMenu();
        }
    }

    private static void displayOrderedQuantity(Product product) {
        int quantity = OrderPool.getOrderedQuantity(product);
        DisplayHelper.displayText("This product has already been ordered.");
        DisplayHelper.displayText("Ordered item quantity: " + quantity);
    }

    private static void checkForNegativeQuantity(int quantity) throws IllegalArgumentException {
        if (quantity < 1) { throw new IllegalArgumentException(); }
    }

    private Product getProduct(int selectedId) throws NullPointerException {
        InventoryDataItem result = products.stream()
                .filter(product -> product.getId() == selectedId)
                .findAny().orElse(null);

        if (result == null) { throw new NullPointerException(); }

        return (Product) result;
    }

    private boolean orderAnyway() {
        DisplayHelper.requestInput("Would you like to place an order anyway? " +
                "Enter 'Y' for yes or 'N' for no. ");
        return answerIsYes();
    }

    private boolean answerIsYes() {
        String input = readUserInput();
        DisplayHelper.navigateToUserMenu(input);

        if (input.equalsIgnoreCase("Y")) {
            return true;
        } else if (input.equalsIgnoreCase("N")) {
            return false;
        } else {
            DisplayHelper.displayText("Invalid input.");
            return answerIsYes();
        }
    }

    private void createOrder(Product product, int quantity) {
        OrderPool.addNewOrder(new Order(product, quantity));

        TransactionPool.addNewTransaction(new Transaction(TransactionType.ORDER, "-", product, quantity));
    }

    private int requestQuantity() {
        int quantity;

        DisplayHelper.requestInput("Enter product quantity.");
        String input = readUserInput();
        DisplayHelper.navigateToUserMenu(input);

        try {
            quantity = Integer.parseInt(input);

            checkForNegativeQuantity(quantity);
        } catch (IllegalArgumentException exception) {
            System.out.println("Invalid quantity.");
            quantity = requestQuantity();
        }
        return quantity;
    }
}
