package utils;

import menus.userMenu.UserMenu;
import users.User;

import java.io.IOException;
import java.util.List;

public class DisplayHelper {

    private static User signedInUser;

    public static void setSignedInUser(User signedInUser) {
        DisplayHelper.signedInUser = signedInUser;
    }

    public static void clearConsole() {
        String operatingSystem = System.getProperty("os.name");

        if (operatingSystem.contains("Windows")) {
            clearConsoleWindows();
        } else {
            clearConsoleUnix();
        }
    }

    public static void listOptions(List<?> options) {
        for (int index = 0; index < options.size(); index++) {
            int number = index + 1;

            System.out.println("[" + number + "] " + options.get(index));
        }
    }

    private static void clearConsoleUnix() {
        String clearConsoleASCIICode = "\033[H\033[2J";

        System.out.print(clearConsoleASCIICode);
        System.out.flush();
    }

    private static void clearConsoleWindows() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException e) {
            String errorMessage = "An unexpected error has occurred, which may result in the program " +
                    "not displaying correctly.";
            System.out.println(errorMessage);
        }
    }

    public static void requestInput(String request){
        System.out.println();
        System.out.println(request);
        System.out.print("Or enter 'X' to go back: ");
    }

    public static void navigateToUserMenu(String input){
        if(input.equalsIgnoreCase("X")){
            new UserMenu(signedInUser);
        }
    }

    public static void displayHeader(String header){
        System.out.println("********** " + header + " **********");
        System.out.println();
    }

    public static void displayText(String text){
        System.out.println(text);
    }
}
