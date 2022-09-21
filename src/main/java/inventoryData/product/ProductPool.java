package inventoryData.product;

import inventoryData.InventoryDataItem;
import utils.Observer;
import utils.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductPool implements Subject {

    private static final List<Product> allProducts = new ArrayList<>();
    private static Observer observer;

    public ProductPool(List<InventoryDataItem> products) {
        for (InventoryDataItem item : products) {
            allProducts.add((Product) item);
        }
    }

    public ProductPool() {
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

    public static Product getProductById(int productId) {
        Product result = allProducts.stream()
                .filter(product -> product.getId() == productId)
                .findAny().orElse(null);
        return result;
    }

    @Override
    public void registerObserver(Observer observer) {
        ProductPool.observer = observer;
    }

}
