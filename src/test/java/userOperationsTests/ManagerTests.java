package userOperationsTests;

import inventoryData.order.Order;
import inventoryData.order.OrderPool;
import inventoryData.product.Product;
import inventoryData.product.ProductPool;
import org.junit.jupiter.api.Test;
import fileOperations.Observer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ManagerTests {

    @Test
    public void addArrivedItemsToProductPoolTest(){
        Product product = new Product(1, "Test Product", 1, "A1");
        Order order = new Order(product, 100);
        int quantity = order.getQuantity();

        ProductPool pool = new ProductPool();

        // Can't run the test if pool's observer is null.
        Observer observer = data -> {};

        pool.registerObserver(observer);

        ProductPool.addItems(product, quantity);

        assertEquals(101, product.getQuantity());
    }

    @Test
    public void removeRejectedOrderFromOrderListTest(){
        OrderPool pool = new OrderPool();
        Product product = new Product(1, "Test Product", 1, "A1");
        Order order = new Order(product, 100);

        Observer observer = data -> {};
        pool.registerObserver(observer);
        
        OrderPool.addNewOrder(order);

        assertEquals(1, OrderPool.getAllOrders().size());

        OrderPool.removeOrder(order);

        assertEquals(0, OrderPool.getAllOrders().size());
    }
}
