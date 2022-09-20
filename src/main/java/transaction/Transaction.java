package transaction;

import product.InventoryDataItem;
import product.Product;

import java.util.List;

public class Transaction extends InventoryDataItem {

    int id;
    private String receiptNumber;
    private Product product;
    int quantity;

    private List<String> headers = List.of("Number","Receipt number", "Product", "Quantity");
    private List<Integer> columnWidths = List.of(5, 10, 20, 10);

    public Transaction(String receiptNumber, Product product, int quantity) {
        this.receiptNumber = receiptNumber;
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
        return List.of(receiptNumber, product.getName(), String.valueOf(quantity));
    }

    @Override
    public String getSavable() {
        return null;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }
}
