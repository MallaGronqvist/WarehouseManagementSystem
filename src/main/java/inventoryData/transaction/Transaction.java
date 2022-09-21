package inventoryData.transaction;

import inventoryData.InventoryDataItem;
import inventoryData.product.Product;
import inventoryData.product.ProductPool;

import java.util.List;

public class Transaction extends InventoryDataItem {

    int id;
    int quantity;
    private final String receiptNumber;
    private final Product product;
    private final List<String> headers = List.of("Number", "Receipt number", "Product", "Quantity");
    private final List<Integer> columnWidths = List.of(5, 10, 20, 10);

    public Transaction(String receiptNumber, Product product, int quantity) {
        this.receiptNumber = receiptNumber;
        this.product = product;
        this.quantity = quantity;
    }

    public Transaction(int transactionId, int productId, int quantity, String receiptNumber) {
        this.id = transactionId;
        Product result = ProductPool.getProductById(productId);
        if (result == null){
            this.product = new Product(productId, "Product not in sale anymore", 0, "X");
        } else {
            this.product = result;
        }
        this.quantity = quantity;
        this.receiptNumber = receiptNumber;
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
        final String DELIMIT = ";";
        String savable = id + DELIMIT + product.getId() + DELIMIT + quantity + DELIMIT + receiptNumber;
        return savable;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
