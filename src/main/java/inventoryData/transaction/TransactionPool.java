package inventoryData.transaction;

import inventoryData.InventoryDataItem;
import inventoryData.product.Product;
import utils.Observer;
import utils.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TransactionPool implements Subject {
    private static final List<Transaction> allTransactions = new ArrayList<>();
    static int idCount;

    private static Observer observer;

    public TransactionPool(List<InventoryDataItem> transactions) {
        for (InventoryDataItem item: transactions) {
            allTransactions.add((Transaction) item);
        }
        int indexLastItem = allTransactions.size() -1;
        this.idCount = allTransactions.get(indexLastItem).getId();
    }

    public static boolean addNewTransaction(Transaction transaction) {
        Transaction result = allTransactions.stream()
                .filter(item -> Objects.equals(item.getReceiptNumber(), transaction.getReceiptNumber()))
                .findAny().orElse(null);

        boolean receiptNumberAlreadyRegistered = (result != null);

        if (receiptNumberAlreadyRegistered) {
            return false;
        }

        transaction.setId(++idCount);
        allTransactions.add(transaction);
        observer.update(allTransactions);
        return true;
    }

    public static Transaction getTransactionByReceipt(String receiptNumber) throws NullPointerException {
        Transaction result = allTransactions.stream()
                .filter(item -> Objects.equals(item.getReceiptNumber(), receiptNumber))
                .findAny().orElse(null);

        if (result == null) { throw new NullPointerException(); }

        return result;
    }

    public static Transaction getTransactionById(int id) throws NullPointerException {
        Transaction result = allTransactions.stream()
                .filter(item -> item.getId() == id)
                .findAny().orElse(null);

        if (result == null) { throw new NullPointerException(); }

        return result;
    }

    public static void removeTransaction(Transaction transaction) throws NullPointerException {
        allTransactions.remove(transaction);
        observer.update(allTransactions);
    }

    public static List<Transaction> getSales(Product selectedProduct) {
        List<Transaction> result = allTransactions.stream().
                filter(transaction -> transaction.getProduct().equals(selectedProduct)).
                filter(transaction -> transaction.getType().equals(TransactionType.REMOVAL)).
                collect(Collectors.toList());
        return result;
    }

    public static List<Transaction> getAllTransactions() {
        return allTransactions;
    }

    @Override
    public void registerObserver(Observer observer) {
        this.observer = observer;
    }
}
