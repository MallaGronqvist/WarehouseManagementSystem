package order;

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
}
