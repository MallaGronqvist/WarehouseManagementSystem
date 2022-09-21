package fileHandlers;

import inventoryData.InventoryDataItem;
import inventoryData.order.OrderPool;
import inventoryData.product.ProductPool;
import inventoryData.transaction.TransactionPool;

import java.io.IOException;
import java.util.List;

public class FileSetUp {
    public static void setUpProductPool() throws IOException {
        ProductPool productPool = null;

        ProductPoolFileHandler fileHandler = new ProductPoolFileHandler("assets/productPool.txt");

        List<InventoryDataItem> products = fileHandler.readFile();
        productPool = new ProductPool(products);

        fileHandler.setSubject(productPool);
        productPool.registerObserver(fileHandler);
    }

    public static void setUpOrderPool() throws IOException {
        OrderPool orderPool = null;
        OrderPoolFileHandler fileHandler = new OrderPoolFileHandler("assets/orderPool.txt");

        List<InventoryDataItem> orders = fileHandler.readFile();
        orderPool = new OrderPool(orders);

        fileHandler.setSubject(orderPool);
        orderPool.registerObserver(fileHandler);
    }

    public static void setUpTransactionPool() throws IOException {
        TransactionPool transactionPool = null;
        TransactionPoolFileHandler fileHandler =
                new TransactionPoolFileHandler("assets/transactionPool.txt");

        List<InventoryDataItem> transactions = fileHandler.readFile();
        transactionPool = new TransactionPool(transactions);

        fileHandler.setSubject(transactionPool);
        transactionPool.registerObserver(fileHandler);
    }

}
