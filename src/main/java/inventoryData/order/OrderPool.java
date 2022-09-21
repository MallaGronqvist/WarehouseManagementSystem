package inventoryData.order;

import inventoryData.InventoryDataItem;
import inventoryData.product.Product;
import utils.Observer;
import utils.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderPool implements Subject {
    private static final List<Order> allOrders = new ArrayList<>();
    private static int idCount = 0;
    private static Observer observer;

    public OrderPool(List<InventoryDataItem> orders) {
        for (InventoryDataItem item : orders) {
            addOrder((Order) item);
        }
    }

    public static List<Order> getOrders(Product selectedProduct) {
        List<Order> result = allOrders.stream()
                .filter(order -> order.getProduct().equals(selectedProduct))
                .collect(Collectors.toList());
        return result;
    }

    // This constructor is used only when reading from file.
    // Hence, no need to notify observer to save to file.
    private void addOrder(Order order) {
        allOrders.add(order);
    }

    public static List<Order> getAllOrders() {
        return allOrders;
    }

    public static void addNeWOrder(Order order) {
        int orderId = ++idCount;
        order.setId(orderId);
        allOrders.add(order);

        observer.update(allOrders);
    }

    // Avoidable nesting??
    public static boolean isAlreadyOrdered(Product product) {
        Order result = allOrders.stream()
                .filter(order -> order.getProduct().equals(product))
                .findAny().orElse(null);

        return (result != null);
    }

    // Can a stream do this?
    public static int getOrderedQuantity(Product product) {
        int quantity = 0;

        List<Order> results = allOrders.stream()
                .filter(order -> order.getProduct().equals(product))
                .collect(Collectors.toList());

        for (Order order: results) {
            quantity += order.getQuantity();
        }

        return quantity;
    }

    public static void removeOrder(Order selectedOrder) {
        allOrders.remove(selectedOrder);
        observer.update(allOrders);
    }

    @Override
    public void registerObserver(Observer observer) { OrderPool.observer = observer; }
}

























