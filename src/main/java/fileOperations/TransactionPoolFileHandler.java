package fileOperations;

import inventoryData.InventoryDataItem;
import inventoryData.Subject;
import inventoryData.transaction.Transaction;
import inventoryData.transaction.TransactionType;

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
    protected InventoryDataItem parseLine(String line) {
        try {
            final String DELIMIT = ";";
            String[] transactionData = line.split(DELIMIT);

            if (transactionData.length == 0) {
                throw new NullPointerException();
            }

            TransactionType type = TransactionType.valueOf(transactionData[0]);
            int transactionId = Integer.parseInt(transactionData[1]);
            int productId = Integer.parseInt(transactionData[2]);
            int quantity = Integer.parseInt(transactionData[3]);
            String receiptNumber = transactionData[4];
            return (new Transaction(type, transactionId, productId, quantity, receiptNumber));
        } catch (NumberFormatException | IndexOutOfBoundsException exception) {
            System.out.println("Warning: Some transaction data may be corrupt or missing.");
        }
        // Extra return statement to keep the compiler happy.
        return null;
    }

    @Override
    public void update(List<? extends InventoryDataItem> allTransactions) {
        saveToFile(allTransactions);
    }
}
