package inventoryData.order;

import fileHandlers.OrderPoolFileHandler;
import inventoryData.InventoryDataItem;
import inventoryData.product.Product;
import utils.Observer;
import utils.Subject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderPool implements Subject {
    private static List<Order> allOrders = new ArrayList<>();
    private static int idCount = 0;
    private static Observer observer;

    public OrderPool(List<InventoryDataItem> orders) {
        for (InventoryDataItem item : orders) {
            allOrders.add((Order) item);
        }
        int indexLastItem = allOrders.size() - 1;
        idCount = allOrders.get(indexLastItem).getId();
    }

    public OrderPool() {
    }

    public static List<Order> getAllOrders() {
        updatePool();
        return allOrders;
    }

    public static void addNewOrder(Order order) {
        int orderId = ++idCount;
        order.setId(orderId);
        allOrders.add(order);

        observer.update(allOrders);
    }

    public static boolean isAlreadyOrdered(Product product) {
        Order result = allOrders.stream()
                .filter(order -> order.getProduct().equals(product))
                .findAny().orElse(null);

        return (result != null);
    }

    public static int getOrderedQuantity(Product product) {
        int quantity = 0;

        List<Order> results = allOrders.stream()
                .filter(order -> order.getProduct().equals(product)).toList();

        for (Order order : results) {
            quantity += order.getQuantity();
        }

        return quantity;
    }

    public static void confirmOrder(Order selectedOrder) {
        selectedOrder.setConfirmed(true);
        observer.update(allOrders);
    }

    public static void removeOrder(Order selectedOrder) {
        allOrders.remove(selectedOrder);
        observer.update(allOrders);
    }

    private static void updatePool() {
        List<InventoryDataItem> items;
        try {
            OrderPoolFileHandler fileHandler = new OrderPoolFileHandler("assets/orderPool.txt");

            items = fileHandler.readFile();
            allOrders.clear();

            for (InventoryDataItem item : items) {
                allOrders.add((Order) item);
            }
            int indexLastItem = allOrders.size() - 1;
            idCount = allOrders.get(indexLastItem).getId();
        } catch (IOException e) {
            System.out.println("Inventory data could not be updated from file.");
        }
    }

    @Override
    public void registerObserver(Observer observer) {
        OrderPool.observer = observer;
    }
}

























