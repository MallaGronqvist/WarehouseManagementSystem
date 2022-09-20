package order;

import product.InventoryDataItem;
import product.Product;

import java.util.List;

public class Order extends InventoryDataItem {
    private int id;
    private Product product;
    private int quantity;
    private List<String> headers = List.of("Order Id", "Product", "Ordered quantity");
    private List<Integer> columnWidths = List.of(10, 20, 16);

    public Order(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    @Override
    public List<String> getHeaders() {
        return headers;
    }

    @Override
    public List<Integer> getColumnWidths() {
        return columnWidths;
    }

    @Override
    public List<String> getDisplayValues() {
        return List.of(String.valueOf(id), product.getName(), String.valueOf(quantity));
    }

    @Override
    public String getSavable() {
        return null;
    }

    public void setId(int orderId) {
        this.id = orderId;
    }
}
