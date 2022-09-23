package fileOperations;

import inventoryData.InventoryDataItem;
import inventoryData.Subject;
import inventoryData.product.Product;

import java.io.IOException;
import java.util.List;

public class ProductPoolFileHandler extends FileHandler implements Observer {
    private Subject subject;

    public ProductPoolFileHandler(String filePath) throws IOException {
        super(filePath);
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    protected InventoryDataItem parseLine(String line) {
        try {
            final String DELIMIT = ";";
            String[] productData = line.split(DELIMIT);

            if (productData.length == 0) {
                throw new NullPointerException();
            }

            int id = Integer.parseInt(productData[0]);
            String name = productData[1];
            int quantity = Integer.parseInt(productData[2]);
            String shelf = productData[3];
            return (new Product(id, name, quantity, shelf));
        } catch (NumberFormatException | IndexOutOfBoundsException exception) {
            System.out.println("Warning: Some product data may be corrupt or missing.");
        }
        // Extra return statement to keep the compiler happy.
        return null;
    }

    @Override
    public void update(List<? extends InventoryDataItem> allProducts) {
        saveToFile(allProducts);
    }
}
