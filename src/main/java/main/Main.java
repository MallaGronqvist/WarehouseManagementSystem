package main;

import menus.mainMenu.MainMenu;
import utils.DisplayHelper;

import java.io.FileNotFoundException;
import java.io.IOException;

import static fileOperations.FileSetUp.*;

public class Main {
    public static void main(String[] args) {
        try {
            setUpProductPool();
            setUpOrderPool();
            setUpTransactionPool();
        } catch (FileNotFoundException fileNotFoundException) {
            displayFileNotFoundError(fileNotFoundException);
            exit();
        } catch (IOException e) {
            displayUnexpectedFileError();
            exit();
        }

        new MainMenu();
    }

    private static void displayFileNotFoundError(FileNotFoundException fileNotFoundException) {
        DisplayHelper.displayText("Inventory data couldn't be loaded properly from file.");
        DisplayHelper.displayText("Following file is missing: " + fileNotFoundException);
        DisplayHelper.displayText("Program must exit.");
    }

    private static void displayUnexpectedFileError() {
        DisplayHelper.displayText("An unexpected error occurred while loading inventory data from file.");
        DisplayHelper.displayText("Program must exit.");
    }

    private static void exit() {
        System.exit(0);
    }
}
