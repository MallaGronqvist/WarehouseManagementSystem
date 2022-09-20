package poolMVC.processingBehavior;

import menus.mainMenu.MainMenu;
import order.Order;
import order.OrderPool;
import product.InventoryDataItem;
import product.Product;

import java.util.List;
import java.util.Scanner;

public class PlaceOrder implements ProcessingBehavior {
    List<? extends InventoryDataItem> products;
    Product selectedProduct;

    @Override
    public void processOption(String input, List<? extends InventoryDataItem> data)
            throws NullPointerException, NumberFormatException {

        this.products = data;
        int selectedId = Integer.parseInt(input);
        this.selectedProduct = getProduct(selectedId);

        prepareOrder();
    }

    private void prepareOrder() {
        printHeader();

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

    private static void printHeader() {
        System.out.println("***CREATE NEW ORDER***");
        System.out.println();
    }

    private static void displayOrderedQuantity(Product product) {
        int quantity = OrderPool.getOrderedQuantity(product);
        System.out.println("This product has already been ordered.");
        System.out.println("Ordered item quantity: " + quantity);
    }

    private static void checkForNegativeQuantity(int quantity) throws IllegalArgumentException {
        if (quantity < 1) {
            throw new IllegalArgumentException();
        }
    }

    private Product getProduct(int selectedId) throws NullPointerException {
        InventoryDataItem product = null;

        for (InventoryDataItem item : products) {
            if (((Product) item).getId() == selectedId) {
                product = item;
            }
        }

        if (product == null) {
            throw new NullPointerException();
        }
        return (Product) product;
    }

    private boolean orderAnyway() {
        System.out.println();
        System.out.println("Would you like to place an order anyway?");
        return answerIsYes();
    }

    private boolean answerIsYes() {
        System.out.print("Enter 'Y' for yes or 'N' for no: ");
        String answer = readUserInput();

        if (answer.equalsIgnoreCase("y")) {
            return true;
        } else if (answer.equalsIgnoreCase("n")) {
            return false;
        } else {
            System.out.println("Invalid input.");
            return answerIsYes();
        }
    }
/*
    private String readUserInput() {
        Scanner keyboard = new Scanner(System.in);
        return keyboard.nextLine();
    }
*/
    private void createOrder(Product product, int quantity) {
        OrderPool.addOrder(new Order(product, quantity));
    }

    private int requestQuantity() {
        int quantity;
        System.out.println("Enter product quantity:");
        String input = readUserInput();
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
