package poolMVC;

import order.Order;
import order.OrderPool;
import product.InventoryDataItem;
import product.Product;

import java.util.List;
import java.util.Scanner;

public class PlaceOrder implements ProcessingBehavior{
    @Override
    public void processOption(String input, List<? extends InventoryDataItem> data)
            throws IndexOutOfBoundsException {

        int selectedId = Integer.parseInt(input);

        InventoryDataItem product = null;

        for (InventoryDataItem item: data) {
            if(((Product) item).getId() == selectedId){
                product = item;
            }
        }

        if(product == null){
            throw new NullPointerException();
        }

        System.out.println("***CREATE NEW ORDER***");
        System.out.println();

        int quantity = requestQuantity();

        createOrder((Product) product, quantity);
    }

    private void createOrder(Product product, int quantity) {
        OrderPool.addOrder(new Order(product, quantity));
    }

    private int requestQuantity() {
        int quantity;
        System.out.println("Enter product quantity:");
        Scanner keyboard = new Scanner(System.in);
        String input = keyboard.nextLine();
        try {
            quantity = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid quantity.");
            quantity = requestQuantity();
        }
        return quantity;
    }
}
