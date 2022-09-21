package fileHandlers;

import inventoryData.InventoryDataItem;
import inventoryData.transaction.Transaction;
import utils.Observer;
import utils.Subject;

import java.io.IOException;
import java.util.List;

public class TransactionPoolFileHandler extends FileHandler implements Observer {
    private Subject subject;

    public TransactionPoolFileHandler(String filePath) throws IOException {
        super(filePath);
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    protected <T extends InventoryDataItem> InventoryDataItem parseLine(String line) {
        try {
            final String DELIMIT = ";";
            String[] transactionData = line.split(DELIMIT);
            int transactionId = Integer.parseInt(transactionData[0]);
            int productId = Integer.parseInt(transactionData[1]);
            int quantity = Integer.parseInt(transactionData[2]);
            String receiptNumber = transactionData[3];
            return (new Transaction(transactionId, productId, quantity, receiptNumber));
        } catch (NumberFormatException | IndexOutOfBoundsException exception) {
            System.out.println("Warning: An error occurred while reading transaction file.");
            System.out.println("Some transaction data may be corrupt.");
        }
        // Extra return statement to keep the compiler happy.
        return null;
    }

    @Override
    public void update(List<? extends InventoryDataItem> allTransactions) {
        saveToFile(allTransactions);
    }
}
