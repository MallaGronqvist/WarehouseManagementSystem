package utils;

import product.InventoryDataItem;
import product.Product;

import java.util.List;

public class ProductPoolFileHandler extends FileHandler implements Observer {

    private Subject subject;
    public ProductPoolFileHandler(String filePath) {
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
            System.out.println("Problem reading product file");
        }
        // Extra return statement to keep the compiler happy.
        return null;
    }

    @Override
    public void update(List<Product> allProducts) {
        saveToFile(allProducts);
    }
}
