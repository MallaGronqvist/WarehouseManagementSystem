package inventoryData.product;

import fileOperations.Observer;
import fileOperations.ProductPoolFileHandler;
import inventoryData.InventoryDataItem;
import inventoryData.Subject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductPool implements Subject {
    private static List<Product> allProducts = new ArrayList<>();
    private static Observer observer;

    public ProductPool(List<InventoryDataItem> products) {
        for (InventoryDataItem item : products) {
            allProducts.add((Product) item);
        }
    }

    public ProductPool() {
    }

    public static List<Product> getAllProducts() {
        updatePool();
        return allProducts;
    }

    public static void removeItems(Product product, int quantity) throws IllegalArgumentException {
        product.removeItems(quantity);

        observer.update(allProducts);
    }

    public static List<Product> getSoonOutOfStockProducts() {

        return allProducts.stream()
                .filter(item -> item.getQuantity() < 6)
                .collect(Collectors.toList());
    }

    public static void addItems(Product product, int quantity) {
        product.addItems(quantity);
        observer.update(allProducts);
    }

    public static Product getProductById(int productId) {

        return allProducts.stream()
                .filter(product -> product.getId() == productId)
                .findAny().orElse(null);
    }

    private static void updatePool() {
        List<InventoryDataItem> items;
        try {
            ProductPoolFileHandler fileHandler = new ProductPoolFileHandler("assets/productPool.txt");

            items = fileHandler.readFile();
            allProducts.clear();

            for (InventoryDataItem item : items) {
                allProducts.add((Product) item);
            }
        } catch (IOException e) {
            System.out.println("Inventory data could not be updated from file.");
        }
    }

    @Override
    public void registerObserver(Observer observer) {
        ProductPool.observer = observer;
    }

}
