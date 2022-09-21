package inventoryData.transaction;

import inventoryData.InventoryDataItem;
import utils.Observer;
import utils.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TransactionPool implements Subject {
    private static final List<Transaction> allTransactions = new ArrayList<>();

    private static Observer observer;

    public TransactionPool(List<InventoryDataItem> transactions) {
        for (InventoryDataItem item: transactions) {
            allTransactions.add((Transaction) item);
        }
    }

    public static boolean addNewTransaction(Transaction transaction) {
        Transaction result = allTransactions.stream()
                .filter(item -> Objects.equals(item.getReceiptNumber(), transaction.getReceiptNumber()))
                .findAny().orElse(null);

        boolean receiptNumberAlreadyRegistered = (result != null);

        if (receiptNumberAlreadyRegistered) {
            return false;
        }

        allTransactions.add(transaction);
        observer.update(allTransactions);
        return true;
    }

    public static Transaction getTransaction(String receiptNumber) throws NullPointerException {
        Transaction result = allTransactions.stream()
                .filter(item -> Objects.equals(item.getReceiptNumber(), receiptNumber))
                .findAny().orElse(null);

        if (result == null) { throw new NullPointerException(); }

        return result;
    }

    public static void removeTransaction(Transaction transaction) throws NullPointerException {
        allTransactions.remove(transaction);
        observer.update(allTransactions);
    }

    @Override
    public void registerObserver(Observer observer) {
        this.observer = observer;
    }
}
