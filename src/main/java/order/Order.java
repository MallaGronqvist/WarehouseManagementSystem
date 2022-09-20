package order;

import product.InventoryDataItem;
import product.Product;

import java.util.List;

public class Order extends InventoryDataItem {
    private int id;
    private Product product;
    private int quantity;

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    boolean confirmed;
    private List<String> headers = List.of("Order Id", "Product", "Ordered quantity", "Status");
    private List<Integer> columnWidths = List.of(10, 20, 16, 20);

    public Order(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.confirmed = false;
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
        if(confirmed){
            status = "Confirmed";
        } else{
            status = "Awaits confirmation";
        }
        return status;
    }

    @Override
    public String getSavable() {
        return null;
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
}
