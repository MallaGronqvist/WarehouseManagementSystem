package main;

import fileHandlers.OrderPoolFileHandler;
import fileHandlers.TransactionPoolFileHandler;
import inventoryData.order.OrderPool;
import inventoryData.transaction.TransactionPool;
import menus.mainMenu.MainMenu;
import inventoryData.InventoryDataItem;
import inventoryData.product.ProductPool;
import fileHandlers.ProductPoolFileHandler;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // let readFile methods throw exceptions and catch them here?

        try {
            setUpProductPool();
            setUpOrderPool();
            setUpTransactionPool();
        } catch (IOException e) {
            System.out.println("Inventory data couldn't be loaded properly from file.");
            System.exit(0);
        }

        new MainMenu();
    }

    private static void setUpProductPool() throws IOException {
        ProductPool productPool = null;

        ProductPoolFileHandler fileHandler = new ProductPoolFileHandler("assets/productPool.txt");

        // Check that file exists?

        List<InventoryDataItem> products = fileHandler.readFile();
        productPool = new ProductPool(products);

        fileHandler.setSubject(productPool);
        productPool.registerObserver(fileHandler);
    }

    private static void setUpOrderPool() throws IOException {
        OrderPool orderPool = null;
        OrderPoolFileHandler fileHandler = new OrderPoolFileHandler("assets/orderPool.txt");

        List<InventoryDataItem> orders = fileHandler.readFile();
        orderPool = new OrderPool(orders);

        fileHandler.setSubject(orderPool);
        orderPool.registerObserver(fileHandler);
    }

    private static void setUpTransactionPool() throws IOException {
        TransactionPool transactionPool = null;
        TransactionPoolFileHandler fileHandler =
                new TransactionPoolFileHandler("assets/transactionPool.txt");

        List<InventoryDataItem> transactions = fileHandler.readFile();
        transactionPool = new TransactionPool(transactions);

        fileHandler.setSubject(transactionPool);
        transactionPool.registerObserver(fileHandler);
    }

}
