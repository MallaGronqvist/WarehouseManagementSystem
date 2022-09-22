package userOperationsTests;

import inventoryData.InventoryDataItem;
import inventoryData.order.Order;
import inventoryData.order.OrderPool;
import inventoryData.product.Product;
import inventoryData.product.ProductPool;
import inventoryData.transaction.Transaction;
import inventoryData.transaction.TransactionPool;
import inventoryData.transaction.TransactionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Observer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CashierTests {
    ProductPool productPool;
    TransactionPool transactionPool;
    OrderPool orderPool;
    Transaction transaction;
    List<InventoryDataItem> products;
    List<InventoryDataItem> transactions;
    List<InventoryDataItem> orders;
    Observer observer;

    @BeforeEach
    void setUp(){
        products = new ArrayList<>();
        transactions = new ArrayList<>();
        orders = new ArrayList<>();

        observer = data -> {};
    }

    @Test
    public void removeItemTest(){
        products.add(new Product(13, "IPhone 13", 10, "A11"));
        productPool = new ProductPool(products);
        productPool.registerObserver(observer);

        Product product = ProductPool.getProductById(13);
        product.removeItems(1);

        assertEquals(product.getQuantity(), 9);
    }

    @Test
    public void removeLastItemTest(){
        products.add(new Product(12, "IPhone Original", 1, "A11"));
        productPool = new ProductPool(products);
        productPool.registerObserver(observer);
        Product product = ProductPool.getProductById(12);

        assertThrows(IllegalArgumentException.class, () -> product.removeItems(1));
    }

    @Test
    public void getTransactionByReceiptTest(){
        products.add(new Product(46, "Samsung Galaxy", 13, "A11"));
        productPool = new ProductPool(products);
        productPool.registerObserver(observer);

        transaction = new Transaction(
                TransactionType.REMOVAL, "9999",
                ProductPool.getProductById(46), 1);
        transactions.add(transaction);
        transactionPool = new TransactionPool(transactions);
        transactionPool.registerObserver(observer);

        Transaction testTransaction = TransactionPool.getTransactionByReceipt("9999");

        assertEquals(transaction.getProduct(), testTransaction.getProduct());
        assertEquals(transaction.getQuantity(), testTransaction.getQuantity());
        assertEquals(transaction.getId(), testTransaction.getId());
        assertEquals(transaction.getType(), testTransaction.getType());
    }

    @Test
    public void getTransactionByInvalidReceiptTest(){
        products.add(new Product(46, "Samsung Galaxy", 13, "A11"));
        productPool = new ProductPool(products);
        productPool.registerObserver(observer);

        transaction = new Transaction(
                TransactionType.REMOVAL, "9999",
                ProductPool.getProductById(46), 1);
        transactions.add(transaction);
        transactionPool = new TransactionPool(transactions);
        transactionPool.registerObserver(observer);

        assertThrows(NullPointerException.class,
                () -> TransactionPool.getTransactionByReceipt("0"));
    }

    @Test
    public void returnItemUpdatedQuantityTest(){
        products.add(new Product(1, "Another test product", 1, "A1"));
        productPool = new ProductPool(products);
        productPool.registerObserver(observer);
        transaction = new Transaction(
                TransactionType.REMOVAL, "9999",
                ProductPool.getProductById(1), 1);

        Product product = transaction.getProduct();
        int quantityBefore = product.getQuantity();
        int addedQuantity = transaction.getQuantity();
        int totalQuantity = quantityBefore + addedQuantity;

        product.addItems(transaction.getQuantity());

        assertEquals(product.getQuantity(), totalQuantity);
    }

    @Test
    public void placeOrderCheckIfProductAlreadyOrderedTest(){
        int quantity = 100;
        Product product = new Product(2, "Another test product", 1, "A1");
        Order order = new Order(product, quantity);
        orders.add(order);
        orderPool = new OrderPool(orders);
        orderPool.registerObserver(observer);

        assertTrue(OrderPool.isAlreadyOrdered(product));
    }



    /*
    @Test
    public void returnItemDeleteTransaction(){

//       transactionPool.addNewTransaction(new Transaction(TransactionType.ORDERED, "-", product, quantity));
        transactionPool.removeTransaction(transaction);

        assertThrows(NullPointerException.class,
                    () -> transactionPool.getTransactionByReceipt(transaction.getReceiptNumber()));
    }

     */
}
