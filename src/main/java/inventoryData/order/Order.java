package inventoryData.order;

import inventoryData.InventoryDataItem;
import inventoryData.product.Product;
import inventoryData.product.ProductPool;

import java.util.List;

public class Order extends InventoryDataItem {
    boolean confirmed;
    private int id;
    private Product product;
    private final int quantity;
    private final List<String> headers = List.of("Order Id", "Product", "Ordered quantity", "Status");
    private final List<Integer> columnWidths = List.of(10, 20, 16, 20);

    // Constructor used when creating new orders.
    public Order(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.confirmed = false;
    }

    // Constructor used when reading orders from file.
    public Order(boolean confirmed, int orderId, int productId, int quantity) {
        this.confirmed = confirmed;
        this.id = orderId;
        this.product = ProductPool.getProductById(productId);

        // In a future scenario, some products might be removed from the product pool.
        // (And perhaps archived in an archive  product pool with their IDs.)
        // Hence, we need to have this safeguard for products that are not found
        // in the product pool when reading orders from file.
        if (this.product == null) {
            this.product = (new Product(productId, "Product not in sale anymore", quantity, "X"));
        }
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
        String status = getStatus();
        return List.of(String.valueOf(id), product.getName(), String.valueOf(quantity), status);
    }

    private String getStatus() {
        String status = "";
        if (confirmed) {
            status = "Confirmed";
        } else {
            status = "Awaits confirmation";
        }
        return status;
    }

    @Override
    public String getSavable() {
        final String DELIMIT = ";";
        return String.valueOf(confirmed) + DELIMIT
                + id + DELIMIT + product.getId()
                + DELIMIT + quantity;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int orderId) {
        this.id = orderId;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
}
