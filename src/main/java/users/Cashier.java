package users;

import inventoryData.product.ProductPool;
import menus.mainMenu.MainMenu;
import menus.userMenu.UserMenu;
import inventoryData.product.Product;
import inventoryData.transaction.Transaction;
import inventoryData.transaction.TransactionPool;
import poolDisplayer.PoolDisplayer;
import poolDisplayer.processingBehavior.ProcessSoldProduct;

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

    @Override
    public void performAction(int selectedOption) throws IndexOutOfBoundsException {
        switch (selectedOption) {
                 case 1 -> viewProductPool();
                 case 2 -> super.viewSoonOutOfStockProducts();
                 case 3 -> returnItem();
                 case 4-> navigateToMainMenu();
            default -> throw new IndexOutOfBoundsException();
        }
        new UserMenu(this);
    }

    @Override
    protected void viewProductPool() {
        new PoolDisplayer(ProductPool.getAllProducts(), new ProcessSoldProduct());
    }

    private void returnItem() {
        System.out.println("*** Return item ***");
        String receiptNumber = requestReceiptNumber();

        if (receiptNumber.equalsIgnoreCase("X")){
            new UserMenu(this);
        }

        Transaction transaction = null;
        try {
            transaction = TransactionPool.getTransactionByReceipt(receiptNumber);
            processReturn(transaction, receiptNumber);
        } catch (NullPointerException e) {
            System.out.println("No transaction was found with the given receipt number.");
            returnItem();
        }
    }

    private void processReturn(Transaction transaction, String receiptNumber) {
        System.out.println("Item found with receiptNumber " + receiptNumber + ":");
        System.out.println(transaction.getProduct().getName());
        if (returnConfirmed()){
            try {
                TransactionPool.removeTransaction(transaction);
                Product product = transaction.getProduct();
                product.addItems(transaction.getQuantity());
                System.out.println("Item was successfully returned.");
            } catch (NullPointerException e) {
                System.out.println("Item has already been returned by another cashier.");
            }
        }
    }

    private static boolean returnConfirmed() {
        System.out.print("Enter 'C' to confirm return, or 'X' to quit: ");
        String answer = readUserInput();
        answer = answer.toUpperCase();

        switch (answer){
            case "C" -> { return true; }
            case "X" -> new MainMenu();
            default -> { return returnConfirmed();}
        }
        // Extra return statement for the compiler.
        return false;
    }

    private static String requestReceiptNumber() {
        System.out.print("Enter receipt number or 'X' to quit: ");
        return readUserInput();
    }

    private static String readUserInput() {
        Scanner keyboard = new Scanner(System.in);
        return keyboard.nextLine();
    }
}
