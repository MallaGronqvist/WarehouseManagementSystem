package fileHandlers;

import inventoryData.InventoryDataItem;
import inventoryData.product.Product;
import utils.Observer;
import utils.Subject;

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
    protected <T extends InventoryDataItem> InventoryDataItem parseLine(String line) {
        try {
            final String DELIMIT = ";";
            String[] productData = line.split(DELIMIT);
            int id = Integer.parseInt(productData[0]);
            String name = productData[1];
            int quantity = Integer.parseInt(productData[2]);
            String shelf = productData[3];
            return (new Product(id, name, quantity, shelf));
        } catch (NumberFormatException | IndexOutOfBoundsException exception) {
            System.out.println("Warning: An error occurred while reading product file.");
            System.out.println("Some product data may be corrupt.");
        }
        // Extra return statement to keep the compiler happy.
        return null;
    }

    @Override
    public void update(List<? extends InventoryDataItem> allProducts) {
        saveToFile(allProducts);
    }
}
