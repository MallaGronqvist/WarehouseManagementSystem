package users;

import inventoryData.product.ProductPool;
import menus.mainMenu.MainMenu;
import menus.userMenu.UserMenu;
import inventoryData.product.Product;
import inventoryData.transaction.Transaction;
import inventoryData.transaction.TransactionPool;
import poolDisplayer.PoolDisplayer;
import poolDisplayer.processingBehavior.RegisterSoldProduct;
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
        new PoolDisplayer(ProductPool.getAllProducts(), new RegisterSoldProduct());
    }

    private void returnItem() {
        DisplayHelper.displayHeader("Return item");
        DisplayHelper.requestInput("Enter receipt number");
        String receiptNumber = readUserInput();

        DisplayHelper.navigateToUserMenu(receiptNumber);

        Transaction transaction = null;
        try {
            transaction = TransactionPool.getTransactionByReceipt(receiptNumber);
            processReturn(transaction, receiptNumber);
        } catch (NullPointerException e) {
            DisplayHelper.displayText("No transaction was found with the given receipt number.");
            returnItem();
        }
    }

    private void processReturn(Transaction transaction, String receiptNumber) {
        DisplayHelper.displayText("Item found with receiptNumber " + receiptNumber + ":");
        DisplayHelper.displayText(transaction.getProduct().getName());

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
        DisplayHelper.requestInput("Enter 'C' to confirm return.");
        String input = readUserInput();
        DisplayHelper.navigateToUserMenu(input);

        String answer = input.toUpperCase();

        switch (answer){
            case "C" -> { return true; }
            default -> { return returnConfirmed();}
        }
    }

    private static String readUserInput() {
        Scanner keyboard = new Scanner(System.in);
        return keyboard.nextLine();
    }
}
