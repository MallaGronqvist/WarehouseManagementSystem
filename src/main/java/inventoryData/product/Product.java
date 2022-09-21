package inventoryData.product;

import inventoryData.InventoryDataItem;

import java.util.List;

public class Product extends InventoryDataItem {
    String shelf;
    private final int id;
    private final String name;
    private int quantity;

    // No price is registered here since that's in the separate payment system,
    // and we don't want to have to change prices in two places.
    // The list of headers is used when printing products.
    private final List<String> headers = List.of("Id", "Product", "Quantity", "Shelf");
    private final List<Integer> columnWidths = List.of(5, 20, 10, 10);

    public Product(int id, String name, int quantity, String shelf) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.shelf = shelf;
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
        return List.of(String.valueOf(id), name, String.valueOf(quantity), shelf);
    }

    @Override
    public String getSavable() {
        final String DELIMIT = ";";
        String savable = id + DELIMIT + name + DELIMIT + quantity + DELIMIT + shelf;
        return savable;
    }

    public void removeItems(int quantity) throws IllegalArgumentException {
        if (this.quantity - quantity < 1 || quantity < 1) {
            throw new IllegalArgumentException();
        }
        this.quantity -= quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getId() {
        return id;
    }

    public void addItems(int quantity) {
        this.quantity += quantity;
    }
}
