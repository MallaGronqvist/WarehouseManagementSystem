package inventoryDataDisplay.processingBehavior;

import inventoryData.transaction.TransactionType;
import inventoryData.InventoryDataItem;
import inventoryData.product.Product;
import inventoryData.product.ProductPool;
import inventoryData.transaction.Transaction;
import inventoryData.transaction.TransactionPool;
import utils.DisplayHelper;


import java.util.List;

public class RegisterSoldProduct implements ProcessingBehavior {
    private List<? extends InventoryDataItem> products;
    private Product selectedProduct;

    @Override
    public void processData(String input, List<? extends InventoryDataItem> data)
            throws NumberFormatException, NullPointerException {

        this.products = data;

        int selectedId = Integer.parseInt(input);
        this.selectedProduct = getProduct(selectedId);

        DisplayHelper.requestInput("Enter receipt number.");
        String receiptNumber = readUserInput();
        DisplayHelper.navigateToUserMenu(receiptNumber);

        registerSoldItem(receiptNumber);

        DisplayHelper.waitForEnter();
    }

    private void registerSoldItem(String receiptNumber) {
        int quantity = 1;

        if(TransactionPool.notInTransactionPool(receiptNumber)){
            try {
                ProductPool.removeProductItems(selectedProduct, quantity);
                registerTransaction(selectedProduct, quantity, receiptNumber);
                DisplayHelper.displayText("Product's item quantity was updated.");
            } catch (IllegalArgumentException e) {
                System.out.println("Last item cannot be removed!");
            }
        } else {
            DisplayHelper.displayText("Invalid receipt number: " +
                    "The receipt number you entered has already been registered.");
        }
    }

    private void registerTransaction(Product product, int quantity, String receiptNumber) {
        TransactionPool.addNewTransaction(
                new Transaction(TransactionType.REMOVAL, receiptNumber, product, quantity));
    }

    private Product getProduct(int selectedId) throws NullPointerException {
        InventoryDataItem result = products.stream()
                .filter(product -> product.getId() == selectedId)
                .findAny().orElse(null);

        if (result == null) { throw new NullPointerException(); }

        return (Product) result;
    }
}
