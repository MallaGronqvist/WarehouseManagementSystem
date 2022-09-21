package poolDisplayer.processingBehavior;

import inventoryData.transaction.TransactionType;
import menus.mainMenu.MainMenu;
import inventoryData.InventoryDataItem;
import inventoryData.product.Product;
import inventoryData.product.ProductPool;
import inventoryData.transaction.Transaction;
import inventoryData.transaction.TransactionPool;

import java.util.List;

public class ProcessSoldProduct implements ProcessingBehavior {
    private List<? extends InventoryDataItem> products;
    private Product selectedProduct;

    @Override
    public void processOption(String input, List<? extends InventoryDataItem> data)
            throws NumberFormatException, NullPointerException {
        this.products = data;

        int selectedId = Integer.parseInt(input);
        this.selectedProduct = getProduct(selectedId);

        String receiptNumber = requestReceiptNumber();

        if (receiptNumber.equalsIgnoreCase("x")) {
            new MainMenu();
        }

        registerSoldItem(receiptNumber);
    }

    private void registerSoldItem(String receiptNumber) {
        //  int quantity = updateProductQuantity((Product) product);
        int quantity = 1;

        if (registerTransaction(selectedProduct, quantity, receiptNumber)) {
            ProductPool.removeProductItems(selectedProduct, quantity);
            System.out.println("Product's item quantity was updated.");
        } else {
            System.out.println("Invalid receipt number: " +
                    "The receipt number you entered has already been registered.");
        }
    }

    private boolean registerTransaction(Product product, int quantity, String receiptNumber) {
        return TransactionPool.addNewTransaction(
                new Transaction(TransactionType.REMOVED, receiptNumber, product, quantity));
    }

    private String requestReceiptNumber() {
        System.out.print("Enter receipt number, or enter 'X' to exit: ");

        return readUserInput();
    }

    private Product getProduct(int selectedId) throws NullPointerException {
        InventoryDataItem result = products.stream()
                .filter(product -> product.getId() == selectedId)
                .findAny().orElse(null);

        if (result == null) { throw new NullPointerException(); }

        return (Product) result;
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
