package main;

import menus.mainMenu.MainMenu;
import product.InventoryDataItem;
import product.ProductPool;
import utils.ProductPoolFileHandler;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ProductPool productPool = null;
        // Load files first.
        ProductPoolFileHandler fileHandler = new ProductPoolFileHandler("assets/productPool.txt");

        // Check that file exists?

        try {
            List<InventoryDataItem> products = fileHandler.readFile();
            productPool = new ProductPool(products);
        } catch (IOException exception) {
            System.out.println("Unable to read file");
        }

        fileHandler.setSubject(productPool);
        productPool.registerObserver(fileHandler);
        fileHandler.saveToFile(ProductPool.getAllProducts());

        new MainMenu();
    }
}
