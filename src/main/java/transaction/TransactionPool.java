package transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TransactionPool {

    private static List<Transaction> allTransactions = new ArrayList<>();

    public static boolean addTransaction(Transaction transaction) {
        List<Transaction> found = allTransactions.stream()
                .filter(item -> Objects.equals(item.getReceiptNumber(), transaction.getReceiptNumber()))
                .toList();
        boolean receiptNumberAlreadyRegistered = (found.size() != 0);

        if (receiptNumberAlreadyRegistered){
            return false;
        }

        allTransactions.add(transaction);
        return true;
    }
}
