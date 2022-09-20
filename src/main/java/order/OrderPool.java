package order;

import product.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderPool {

    private static final List<Order> allOrders = new ArrayList<>();
    private static int count = 0;

    public static List<Order> getAllOrders() {
        return allOrders;
    }

    public static void addOrder(Order order) {
        int orderId = ++count;
        order.setId(orderId);
        allOrders.add(order);
    }

    // Avoidable nesting??
    public static boolean isAlreadyOrdered(Product product) {
        boolean found = false;
        for (Order order: allOrders) {
            if(order.getProduct().equals(product)){
                found = true;
            }
        }
        return found;
    }

    public static int getOrderedQuantity(Product product) {
        int quantity = 0;
        for(Order order : allOrders){
            if(order.getProduct().equals(product)){
                quantity += order.getQuantity();
            }
        }
        return quantity;
    }

    public static void removeOrder(Order selectedOrder) {
        allOrders.remove(selectedOrder);
    }
}
