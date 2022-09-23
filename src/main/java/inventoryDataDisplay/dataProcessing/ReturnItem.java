package inventoryDataDisplay.dataProcessing;

import inventoryData.product.Product;
import inventoryData.product.ProductPool;
import inventoryData.transaction.Transaction;
import inventoryData.transaction.TransactionPool;
import utils.DisplayHelper;

import java.util.Scanner;

public class ReturnItem {
    public ReturnItem() {
        DisplayHelper.clearConsole();
        DisplayHelper.displayHeader("Return item");

        returnItem();

    }

    private void returnItem() {
        DisplayHelper.requestInput("Enter receipt number.");
        String receiptNumber = readUserInput();

        DisplayHelper.navigateToUserMenu(receiptNumber);

        Transaction transaction;
        try {
            if (receiptNumber.equals("-")) {
                throw new IllegalArgumentException();
            }
            transaction = TransactionPool.getTransactionByReceipt(receiptNumber);
            processReturn(transaction, receiptNumber);
        } catch (NullPointerException | IllegalArgumentException exception) {
            DisplayHelper.displayText("No transaction was found with the given receipt number.");
            DisplayHelper.waitForEnter();
            returnItem();
        }
    }

    private static boolean returnConfirmed() {
        DisplayHelper.requestInput("Enter 'C' to confirm return.");
        String input = readUserInput();
        DisplayHelper.navigateToUserMenu(input);
        String answer = input.toUpperCase();

        if ("C".equals(answer)) {
            return true;
        }
        return returnConfirmed();
    }

    private static String readUserInput() {
        Scanner keyboard = new Scanner(System.in);
        return keyboard.nextLine();
    }

    private void processReturn(Transaction transaction, String receiptNumber) {
        DisplayHelper.displayText("Item found with receiptNumber " + receiptNumber + ":");
        DisplayHelper.displayText(transaction.getProduct().getName());

        if (returnConfirmed()) {
            try {
                TransactionPool.removeTransaction(transaction);
                Product product = transaction.getProduct();
                ProductPool.addItems(product, transaction.getQuantity());

                DisplayHelper.displayText("Item was successfully returned.");
                DisplayHelper.waitForEnter();
            } catch (NullPointerException e) {
                DisplayHelper.displayText("Item has already been returned by another cashier.");
                DisplayHelper.waitForEnter();
            }
        }
    }
}
