package userOperationsTests;

import fileOperations.Observer;
import inventoryData.order.Order;
import inventoryData.order.OrderPool;
import inventoryData.product.Product;
import inventoryData.product.ProductPool;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ManagerTests {

    @Test
    public void addArrivedItemsToProductPoolTest() {
        Product product = new Product(1, "Test Product", 1, "A1");
        Order order = new Order(product, 100);
        int quantity = order.getQuantity();

        ProductPool pool = new ProductPool();

        Observer observer = data -> {
        };

        pool.registerObserver(observer);

        ProductPool.addItems(product, quantity);

        assertEquals(101, product.getQuantity());
    }

    @Test
    public void confirmOrderListTest() {
        OrderPool pool = new OrderPool();
        Product product = new Product(1, "Test Product", 1, "A1");
        Order order = new Order(product, 100);

        Observer observer = data -> {
        };
        pool.registerObserver(observer);

        OrderPool.addNewOrder(order);

        OrderPool.confirmOrder(order);

        assertTrue(order.isConfirmed());
    }
}
