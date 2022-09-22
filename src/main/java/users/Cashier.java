package users;

import inventoryData.product.Product;
import inventoryData.product.ProductPool;
import inventoryData.transaction.Transaction;
import inventoryData.transaction.TransactionPool;
import menus.userMenu.UserMenu;
import inventoryDataDisplay.InventoryDataMenu;
import inventoryDataDisplay.processingBehavior.RegisterSoldProduct;
import utils.DisplayHelper;

import java.util.List;
import java.util.Scanner;

public class Cashier extends User {

    public Cashier() {
        super.actions = List.of(
                "Register sold item",
                "View 'soon out of stock' products / Place order",
                "Return item",
                "Sign out");
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

    @Override
    public void performAction(int selectedOption) throws IndexOutOfBoundsException {
        switch (selectedOption) {
            case 1 -> viewProductPool();
            case 2 -> super.viewSoonOutOfStockProducts();
            case 3 -> returnItem();
            case 4 -> navigateToMainMenu();
            default -> throw new IndexOutOfBoundsException();
        }
        new UserMenu(this);
    }

    @Override
    protected void viewProductPool() {
        new InventoryDataMenu(ProductPool.getAllProducts(), new RegisterSoldProduct());
    }

    private void returnItem() {
        DisplayHelper.clearConsole();
        DisplayHelper.displayHeader("Return item");
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

    private void processReturn(Transaction transaction, String receiptNumber) {
        DisplayHelper.displayText("Item found with receiptNumber " + receiptNumber + ":");
        DisplayHelper.displayText(transaction.getProduct().getName());

        if (returnConfirmed()) {
            try {
                TransactionPool.removeTransaction(transaction);
                Product product = transaction.getProduct();
                ProductPool.addItemsToProduct(product, transaction.getQuantity());

                DisplayHelper.displayText("Item was successfully returned.");
                DisplayHelper.waitForEnter();
            } catch (NullPointerException e) {
                DisplayHelper.displayText("Item has already been returned by another cashier.");
            }
        }
    }
}
