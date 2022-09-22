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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CashierTests {

    ProductPool productPool;
    TransactionPool transactionPool;

    OrderPool orderPool;

    Transaction transaction;

    // static pools mix it up!!
    @BeforeEach
    void setUp(){
        List<InventoryDataItem> products = new ArrayList<>();
        products.add(new Product(46, "Samsung Galaxy", 13, "A11"));
        products.add(new Product(13, "IPhone 13", 10, "A11"));
        products.add(new Product(12, "IPhone Original", 1, "A11"));
        productPool = new ProductPool(products);

        List<InventoryDataItem> transactions = new ArrayList<>();
        transaction = new Transaction(
                TransactionType.REMOVAL, "9999",
                ProductPool.getProductById(46), 1);
        transactions.add(transaction);
        transactionPool = new TransactionPool(transactions);

        List<InventoryDataItem> orders = new ArrayList<>();
        orders.add(new Order(ProductPool.getProductById(13), 50));
        orderPool = new OrderPool(orders);

        Observer observer = data -> {};

        orderPool.registerObserver(observer);

        transactionPool.registerObserver(observer);
    }

    @Test
    public void removeItemTest(){
        Product product = ProductPool.getProductById(13);

        product.removeItems(1);

        assertEquals(product.getQuantity(), 9);
    }

    @Test
    public void removeLastItemTest(){
        Product product = ProductPool.getProductById(12);

        assertThrows(IllegalArgumentException.class, () -> product.removeItems(1));
    }

    @Test
    public void getTransactionByReceiptTest(){

        Transaction testTransaction = TransactionPool.getTransactionByReceipt("9999");
        assertEquals(transaction.getReceiptNumber(), testTransaction.getReceiptNumber());
    }

    @Test
    public void getTransactionByInvalidReceiptTest(){

        assertThrows(NullPointerException.class, () -> TransactionPool.getTransactionByReceipt("0"));

    }
/*
    @Test
    public void placeOrderTest(){
        Product product = productPool.getProductById(13);
        int quantity = 100;

        Order testOrder = new Order(product, quantity);

        orderPool.addNeWOrder(testOrder);

 //       transactionPool.addNewTransaction(new Transaction(TransactionType.ORDERED, "-", product, quantity));

        assertEquals(testOrder, orderPool.getOrderById(123));
    }
*/
    @Test
    public void returnItemUpdatedQuantityTest(){

        Product product = transaction.getProduct();
        int quantityBefore = product.getQuantity();
        int addedQuantity = transaction.getQuantity();
        int totalQuantity = quantityBefore + addedQuantity;
        product.addItems(transaction.getQuantity());

        assertEquals(product.getQuantity(), totalQuantity);
    }
    /*
    @Test
    public void returnItemDeleteTransaction(){


        transactionPool.removeTransaction(transaction);

        assertThrows(NullPointerException.class,
                    () -> transactionPool.getTransactionByReceipt(transaction.getReceiptNumber()));
    }

     */
}