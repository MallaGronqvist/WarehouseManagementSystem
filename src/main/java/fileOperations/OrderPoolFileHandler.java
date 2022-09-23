package fileOperations;

import inventoryData.InventoryDataItem;
import inventoryData.order.Order;
import utils.Observer;
import utils.Subject;

import java.io.IOException;
import java.util.List;

public class OrderPoolFileHandler extends FileHandler implements Observer {

    private Subject subject;

    public OrderPoolFileHandler(String filePath) throws IOException {
        super(filePath);
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    protected InventoryDataItem parseLine(String line) {
        try {
            final String DELIMIT = ";";
            String[] orderData = line.split(DELIMIT);

            if (orderData.length == 0){ throw new NullPointerException(); }

            boolean confirmed = Boolean.parseBoolean(orderData[0]);
            int orderId = Integer.parseInt(orderData[1]);
            int productId = Integer.parseInt(orderData[2]);
            int quantity = Integer.parseInt(orderData[3]);

            return (new Order(confirmed, orderId, productId, quantity));
        } catch (NumberFormatException | NullPointerException| IndexOutOfBoundsException exception) {
            System.out.println("Warning: Some order data may be corrupt or missing.");
        }
        // Extra return statement to keep the compiler happy.
        return null;
    }

    @Override
    public void update(List<? extends InventoryDataItem> allOrders) {
        saveToFile(allOrders);
    }
}
