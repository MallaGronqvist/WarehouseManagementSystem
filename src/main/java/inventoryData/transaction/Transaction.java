package inventoryData.transaction;

import inventoryData.InventoryDataItem;
import inventoryData.product.Product;
import inventoryData.product.ProductPool;

import java.util.List;

public class Transaction extends InventoryDataItem {
    private int id;
    private final String receiptNumber;
    private final Product product;
    private final List<String> headers = List.of("Id", "Transaction Type", "Product", "Quantity", "Receipt number");
    private final List<Integer> columnWidths = List.of(5, 20, 20, 10, 15);
    private final int quantity;
    private final TransactionType type;


    // Constructor for new transactions.
    public Transaction(TransactionType type, String receiptNumber, Product product, int quantity) {
        this.type = type;
        this.receiptNumber = receiptNumber;
        this.product = product;
        this.quantity = quantity;
    }

    // Constructor used when reading from file.
    public Transaction(TransactionType type, int transactionId, int productId, int quantity, String receiptNumber) {
        this.type = type;
        this.id = transactionId;
        Product result = ProductPool.getProductById(productId);
        if (result == null) {
            this.product = new Product(productId, "Product not in sale anymore", 0, "X");
        } else {
            this.product = result;
        }
        this.quantity = quantity;
        this.receiptNumber = receiptNumber;
    }

    @Override
    public List<String> getDisplayValues() {
        return List.of(String.valueOf(id), type.toString(), product.getName(), String.valueOf(quantity), receiptNumber);
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
    public String getSavable() {
        final String DELIMIT = ";";
        return type + DELIMIT + id + DELIMIT + product.getId() +
                DELIMIT + quantity + DELIMIT + receiptNumber;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public TransactionType getType() {
        return type;
    }
}
