package product;

import utils.Observer;
import utils.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductPool implements Subject {

    private static List<Product> allProducts = new ArrayList<>();
    private static Observer observer;

    public ProductPool(List<InventoryDataItem> products) {
        for (InventoryDataItem item : products) {
            addProduct((Product) item);
        }
    }

    public static List<Product> getAllProducts() {
        return allProducts;
    }

    public static void removeProductItems(Product product, int quantity) {
        product.removeItems(quantity);

        observer.update(allProducts);
    }

    public static List<Product> getSoonOutOfStockProducts() {
        List<Product> soonOutOfStock = allProducts.stream()
                .filter(item -> item.getQuantity() < 6)
                .collect(Collectors.toList());

        return soonOutOfStock;
    }

    public static void addItemsToProduct(Product product, int quantity) {
        product.addItems(quantity);
        observer.update(allProducts);
    }

    public void addProduct(Product product) {
        allProducts.add(product);
    }

    @Override
    public void registerObserver(Observer observer) {
        this.observer = observer;
    }

    @Override
    public void removeObserver(Observer observer) {

    }

    // getState
    // SetState
}
