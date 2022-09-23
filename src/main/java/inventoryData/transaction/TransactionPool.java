package inventoryData.transaction;

import fileOperations.Observer;
import fileOperations.TransactionPoolFileHandler;
import inventoryData.InventoryDataItem;
import inventoryData.Subject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TransactionPool implements Subject {
    static int idCount;
    private static List<Transaction> allTransactions = new ArrayList<>();
    private static Observer observer;

    public TransactionPool(List<InventoryDataItem> transactions) {
        for (InventoryDataItem item : transactions) {
            allTransactions.add((Transaction) item);
        }
        if (allTransactions.size() != 0) {
            int indexLastItem = allTransactions.size() - 1;
            idCount = allTransactions.get(indexLastItem).getId();
        }
    }

    public static boolean notRegistered(String receiptNumber) {
        Transaction result = allTransactions.stream()
                .filter(item -> Objects.equals(item.getReceiptNumber(), receiptNumber))
                .findAny().orElse(null);

        return (result == null);
    }

    public static void addNewTransaction(Transaction transaction) {
        transaction.setId(++idCount);
        allTransactions.add(transaction);
        observer.update(allTransactions);
    }

    public static Transaction getTransactionByReceipt(String receiptNumber) throws NullPointerException {
        Transaction result = allTransactions.stream()
                .filter(item -> Objects.equals(item.getReceiptNumber(), receiptNumber))
                .findAny().orElse(null);

        if (result == null) {
            throw new NullPointerException();
        }

        return result;
    }

    public static void removeTransaction(Transaction transaction) throws NullPointerException {
        allTransactions.remove(transaction);
        observer.update(allTransactions);
    }

    public static List<Transaction> getAllTransactions() {
        updatePool();
        return allTransactions;
    }

    private static void updatePool() {
        List<InventoryDataItem> items;
        try {
            TransactionPoolFileHandler fileHandler =
                    new TransactionPoolFileHandler("assets/transactionPool.txt");
            items = fileHandler.readFile();

            allTransactions.clear();

            for (InventoryDataItem item : items) {
                allTransactions.add((Transaction) item);
            }
            if (allTransactions.size() != 0) {
                int indexLastItem = allTransactions.size() - 1;
                idCount = allTransactions.get(indexLastItem).getId();
            }
        } catch (IOException e) {
            System.out.println("Inventory data could not be updated from file.");
        }
    }

    @Override
    public void registerObserver(Observer fileHandler) {
        observer = fileHandler;
    }
}
