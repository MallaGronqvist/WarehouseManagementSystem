package main;

import menus.mainMenu.MainMenu;

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
        System.out.println("Inventory data couldn't be loaded properly from file.");
        System.out.println("Following file is missing: " + fileNotFoundException);
        System.out.println("Program must exit.");
    }

    private static void displayUnexpectedFileError() {
        System.out.println("An unexpected error occurred while loading inventory data from file.");
        System.out.println("Program must exit.");
    }

    private static void exit() {
        System.exit(0);
    }
}
