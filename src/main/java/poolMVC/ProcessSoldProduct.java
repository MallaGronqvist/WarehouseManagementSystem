package poolMVC;

import menus.mainMenu.MainMenu;
import product.InventoryDataItem;
import product.Product;
import product.ProductPool;
import transaction.Transaction;
import transaction.TransactionPool;

import java.util.List;
import java.util.Scanner;

public class ProcessSoldProduct implements ProcessingBehavior{

    @Override
    public void processOption(String input, List<? extends InventoryDataItem> data)
            throws NumberFormatException, NullPointerException {

        int selectedId = Integer.parseInt(input);
        InventoryDataItem product = null;

        for (InventoryDataItem item: data) {
            if(((Product) item).getId() == selectedId){
                product = item;
            }
        }

        if(product == null){
            throw new NullPointerException();
        }

        String receiptNumber = requestReceiptNumber();

        if(receiptNumber.equalsIgnoreCase("x")){
            new MainMenu();
        } else {
            //        int quantity = updateProductQuantity((Product) product);
            int quantity = 1;

            if (registerTransaction((Product) product, quantity, receiptNumber)) {
                ProductPool.removeProductItems((Product)product, quantity);
                System.out.println("Product's item quantity was updated.");
            } else {
                System.out.println("Invalid receipt number: " +
                        "The receipt number you entered has already been registered.");
            }
        }
    }

    private boolean registerTransaction(Product product, int quantity, String receiptNumber) {
        return TransactionPool.addTransaction(new Transaction(receiptNumber, product, quantity));
    }

    private String requestReceiptNumber() {
        System.out.print("Enter receipt number, or enter 'x' to exit: ");
        Scanner keyboard = new Scanner(System.in);
        String input = keyboard.nextLine();
        return input;
    }
/*
    private int updateProductQuantity(Product product) {
        int quantity = requestQuantity();

        try {
            ProductPool.removeProductItems(product, quantity);
            System.out.println("Product's item quantity was updated.");
        } catch (IllegalArgumentException exception) {
            System.out.println("Invalid quantity.");
            quantity = updateProductQuantity(product);
        }

        return quantity;
    }

    private static int requestQuantity() {
        int quantity = 0;
        try {
            System.out.print("Enter quantity of sold items:");
            Scanner keyboard = new Scanner(System.in);
            String input = keyboard.nextLine();
            quantity = Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            System.out.println("⚠️ Invalid input.");
        }

        return quantity;
    }

 */
}
